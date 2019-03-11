package com.haili.ins.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Twitter_Snowflake<br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 */
public class SnowflakeIdWorker {

    // ==============================Fields===========================================
    /** 开始时间截 (2015-01-01) */
    private final long START_STMP  = 1551255812000L;

    private static final long ZERO = 0L;

    /**
     * 每一部分占用的位数
     */
    private static final long SEQUENCE_BIT = 12;  //序列号占用的位数
    private static final long WORKER_BIT = 5;     //工作节点标识占用的位数
    private static final long DATACENTER_BIT = 5; //数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private static final long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private static final long MAX_WORKER_NUM = -1L ^ (-1L << WORKER_BIT);
    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private static final long WORKER_LEFT = SEQUENCE_BIT;
    private static final long DATACENTER_LEFT = SEQUENCE_BIT + WORKER_BIT;
    private static final long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private final long datacenterId;  //数据中心
    private final long workerId;      //工作节点标识
    private long sequence = 0L;       //序列号
    private long lastStmp = -1L;      //上一次时间戳

    public SnowflakeIdWorker(long datacenterId, long workerId) {

        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException(
                    "datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (workerId > MAX_WORKER_NUM || workerId < 0) {
            throw new IllegalArgumentException(
                    "workerId can't be greater than MAX_WORKER_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.workerId = workerId;
    }

    /**
     * 产生下一个ID
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("clock moved backwards, refusing to generate id.");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == ZERO) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | workerId << WORKER_LEFT               //工作节点标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取数据中心ID
     * @author sunk
     */
    public static long getDatacenterId(long id) {

        long shift = id >> DATACENTER_LEFT;
        long mask = (1 << DATACENTER_BIT) - 1;
        long dcid = shift & mask;

        String idBit = Long.toBinaryString(id);
        String shiftBit = Long.toBinaryString(shift);
        String maskBit = Long.toBinaryString(mask);
        String dcidBit = Long.toBinaryString(dcid);

        System.out.println("id ：" + id + " | " + idBit);
        System.out.println("shift : " + shift + " | " + shiftBit);
        System.out.println("mask : " + mask + " | " + maskBit);
        System.out.println("dcid : " + dcid + " | " + dcidBit);

        return shift & mask;
    }

    /**
     * 获取工作节点ID
     * @author sunk
     */
    public static long getWorkerId(long id) {

        long shift = id >> WORKER_LEFT;
        long mask = (1 << WORKER_BIT) - 1;
        return shift & mask;
    }

    public Map<String,Object> parseInfo(String id) {
        id = Long.toBinaryString(Long.parseLong(id));
        int len = id.length();
//        JSONObject jsonObject = new JSONObject();
        Map<String,Object> resultMap = new HashMap<>();
        long sequenceStart = len < WORKER_LEFT  ? 0 : len - WORKER_LEFT ;
        long workerStart = len < DATACENTER_LEFT  ? 0 : len - DATACENTER_LEFT ;
        long timeStart = len < TIMESTMP_LEFT  ? 0 : len - TIMESTMP_LEFT ;
        String sequence = id.substring((int)sequenceStart , len);
        String workerId = sequenceStart == 0 ? "0" : id.substring((int)workerStart, (int)sequenceStart);
        String dataCenterId = workerStart == 0 ? "0" : id.substring((int)timeStart, (int)workerStart);
        String time = timeStart == 0 ? "0" : id.substring(0, (int)timeStart);
        int sequenceInt = Integer.valueOf(sequence, 2);
        resultMap.put("sequence", sequenceInt);
        int workerIdInt = Integer.valueOf(workerId, 2);
        resultMap.put("workerId", workerIdInt);
        int dataCenterIdInt = Integer.valueOf(dataCenterId, 2);
        resultMap.put("dataCenter", dataCenterIdInt);
        long diffTime = Long.parseLong(time, 2);
        long timeLong = diffTime + START_STMP;
        String date = DateUtil.transferLongToDate(DateFormatType.YYYYMMDDHHMMSSSSS,timeLong);
        resultMap.put("date", date);
        return resultMap;
    }


    //==============================Test=============================================
    /** 测试 */
    public static void main(String[] args) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(5, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(Long.toBinaryString(id));
            System.out.println(id);
            System.out.println(JSONUtil.toJson(idWorker.parseInfo(String.valueOf(id))));
        }
    }
}
