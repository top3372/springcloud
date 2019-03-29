package com.haili.ins.common.enums;


public enum ResponseCodeEnum {
	SUCCESS("000000", "请求处理成功"),

	PROCESSING("0000001", "请求已受理"),

	PROCESS_ERROR("000002", "请求受理失败"),

	PROCESS_CANCEL("000003", "请求受理撤销"),

	PROCESS_SUCCESS("000004", "请求受理成功"),

	MSG_PARSING_FAILURE("000005", "报文解析失败"),

	COMPRESS_FAILURE("000006", "报文压缩处理失败"),

	UNCOMPRESS_FAILURE("000007", "报文解压缩处理失败"),

	INVALID_PARAM("000008", "无效的请求参数"),

	VALID_TARGETSYSCODE_FAILURE("000009", "目标系统校验失败"),

	VALID_DATAMSGSIZE_FAILURE("000010", "请求报文内容长度校验失败"),

	UNDEFINED_SERVICE("000011", "请求服务代码未定义"),

	PARAM_VRFY_FAIL("000012", "参数校验未通过"),

	ENCRYP_MACHINE_HANDLE_FAILURE("000013", "加密机处理失败"),

	BUSI_ERROR("000014", "内部服务异常"),

	API_VERSION_ERROR("000015", "调用接口服务版本错误"),

	/** token 过期 **/
	TOKEN_TIMEOUT_CODE("000016","token 过期"),
	/** 禁止访问 **/
	NO_AUTH_CODE("000017","禁止访问"),

	VERIFY_SUCCESS("000018","校验成功"),

	VERIFY_ERROR("000019","校验失败"),

	DB_ERROR("999990", "数据库执行失败"),

	DB_BUSY("999991", "数据库执行忙"),

	REMOTE_CALL_FAILURE("999992", "远程调用失败"),

	UNDEFINED_ERROR("999993", "服务处理失败"),


	FAILURE("999999","失败"),

	LOGIN_FAILURE("10001","登录失败"),

	LOGOUT_FAILURE("10002","注销失败"),

	_3001("3001", "账户状态异常"),
	_3002("3002", "帐户尚有余额不允许销户"),
	_3003("3003", "帐户不存在"),
	_3004("3004", "帐户已过期"),
	_3005("3005", "帐户未完成交易，不允许销户"),
	_3006("3006", "帐户余额超限"),
	_3007("3007", "帐户可用余额不足"),
	_3008("3008", "账户交易不匹配"),
	_3009("3009", "账户密文异常"),
	_3010("3010", "非法账户类型"),
	_3011("3011", "超出账户消费范围"),
	_3012("3012", "非法账户标识"),
	_3013("3013", "开户失败"),
	_3014("3014", "账户冻结异常"),
	_3015("3015", "原系统跟踪号不存在"),
	_3016("3016", "解冻金额大于账户冻结金额"),
	_3017("3017", "冻结金额大于账户可用金额"),
	_3018("3018", "账户解冻异常"),
	_3019("3019", "账户名称重复"),
	_3020("3020", "账户已经发生过交易,无法撤销"),
	_4002("4002", "交易金额非法"),
	_3021("3021", "帐户号不存在"),


	;
	private String code;
	private String desc;

	ResponseCodeEnum(String code, String desc) {

		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static ResponseCodeEnum getResponseCodeEnum(String value) {
		if (value != null) {
			for (ResponseCodeEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return nameEnum;
				}
			}
		}
		return null;
	}

	public static boolean isResponseCodeEnum(String value) {
		if (value != null) {
			for (ResponseCodeEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}


	

}
