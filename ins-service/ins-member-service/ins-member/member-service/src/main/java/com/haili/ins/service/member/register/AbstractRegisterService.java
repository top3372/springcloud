package com.haili.ins.service.member.register;

import com.haili.ins.common.constants.RegexConstant;
import com.haili.ins.common.utils.RegexUtil;
import com.haili.ins.common.utils.SecurityUtil;
import com.haili.ins.dto.member.request.RegisterRequest;
import com.haili.ins.dto.member.response.MemberInfo;
import com.haili.ins.enums.member.*;
import com.haili.ins.exception.ServiceException;
import com.haili.ins.feign.LeafIdFeign;
import com.haili.ins.dao.mapper.MemberBaseMapper;
import com.haili.ins.dao.mapper.MemberLoginInfoMapper;
import com.haili.ins.dao.mapper.MemberRegLogMapper;
import com.haili.ins.dao.model.MemberBase;
import com.haili.ins.dao.model.MemberLoginInfo;
import com.haili.ins.dao.model.MemberRegLog;
import com.haili.ins.utils.MaskUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author MaTang
 */
public abstract class AbstractRegisterService {

    @Resource
    protected MemberBaseMapper memberBaseMapper;

    @Resource
    protected MemberLoginInfoMapper memberLoginInfoMapper;

    @Resource
    protected MemberRegLogMapper memberRegLogMapper;

    @Resource
    protected LeafIdFeign leafIdFeign;

    /**
     * @param registerRequest
     */
    public abstract void registerBefore(RegisterRequest registerRequest);

    /**
     * @param registerRequest
     */
    public abstract void registerAfter(RegisterRequest registerRequest);

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ServiceException.class)
    public MemberInfo register(RegisterRequest registerRequest) {
        registerBefore(registerRequest);
        //生成分布式唯一id
        String memberId = leafIdFeign.getSnowflakeID("member");


        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        //生成随机唯一用户名
        String userName = registerRequest.getRegTerminal() + "_" + memberId;
        String userNameMask = registerRequest.getRegTerminal() + "_" + MaskUtil.getNameMask(memberId);
        memberInfo.setUserName(userName);
        memberInfo.setUserNameMask(userNameMask);

        if (RegexUtil.match(RegexConstant.MOBILE_REGEX, registerRequest.getRegisterName())) {
            memberInfo.setMobile(registerRequest.getRegisterName());
            memberInfo.setMobileMask(SecurityUtil.hidePhone(registerRequest.getRegisterName()));
        } else if (RegexUtil.match(RegexConstant.EMAIL_REGEX, registerRequest.getRegisterName())) {
            memberInfo.setEmail(registerRequest.getRegisterName());
            memberInfo.setEmailMask(SecurityUtil.hideEmail(registerRequest.getRegisterName()));
        }

        memberInfo.setMemberType(MemberTypeEnum.MEMBER_TYPE_PERSONAL.getCode());

        memberInfo.setIsBindBankCard(BindCardStatusEnum.BANK_CARD_UNBINDING.getCode());

        memberInfo.setRealNameStatus(AuthStatusEnum.AUTH_WAIT.getCode());

        memberInfo.setCertificationState(CertificationStepEnum.INIT_CERTIFICATION_STATE.getCode());

        memberInfo.setIsSetLoginPwd(ConfigStatusEnum.NO_SET.getCode());

        memberInfo.setBuyFlag(BuyFlagEnum.NO_PURCHASE.getCode());

        memberInfo.setIsModifyUserName(ConfigStatusEnum.NO_SET.getCode());

        MemberBase memberBase = new MemberBase();

        memberBase.setMemberId(memberId);
        memberBase.setRegAccount(userName);
        memberBase.setRegAccountMask(userNameMask);
        memberBase.setMemberType(MemberTypeEnum.MEMBER_TYPE_PERSONAL.getCode());
        memberBase.setMemberLevel(MemberLevelEnum.MEMBER_LEVEL_0.getCode());
        memberBase.setMemberGrowValue("0");
        memberBase.setRiskLevel(MemberRiskLevelEnum.RISK_LEVEL_0.getCode());
        memberBase.setPoint("0");
        memberBase.setStatus(LockStatusEnum.STATUS_NORMAL.getCode());
        memberBase.setTxnStatus(TxnStatusEnum.TXN_STATUS_NORMAL.getCode());
        memberBase.setHeaderFlag(ConfigStatusEnum.NO_SET.getCode());

        if (RegexUtil.match(RegexConstant.MOBILE_REGEX, registerRequest.getRegisterName())) {
            memberBase.setMobile(registerRequest.getRegisterName());
            memberBase.setMobileMask(SecurityUtil.hidePhone(registerRequest.getRegisterName()));
        } else if (RegexUtil.match(RegexConstant.EMAIL_REGEX, registerRequest.getRegisterName())) {
            memberBase.setEmail(registerRequest.getRegisterName());
            memberBase.setEmailMask(SecurityUtil.hideEmail(registerRequest.getRegisterName()));
        }
//        memberBase.setNickName(copy.getNickName());
//        memberBase.setNickNameMask(copy.getNickNameMask());
        memberBase.setAuthStatus(AuthStatusEnum.AUTH_WAIT.getCode());
        memberBase.setCardAuthType(AuthStatusEnum.AUTH_WAIT.getCode());
        memberBase.setPayPwdStatus(ConfigStatusEnum.NO_SET.getCode());
        memberBase.setEmailAuthStatus(AuthStatusEnum.AUTH_WAIT.getCode());
        memberBase.setCurrentCertificationStep(CertificationStepEnum.INIT_CERTIFICATION_STATE.getCode());
        memberBase.setSourceCode(registerRequest.getSource());
//        memberBase.setCreateDate(copy.getCreateDate());
//        memberBase.setCreateBy(copy.getCreateBy());
//        memberBase.setUpdateDate(copy.getUpdateDate());
//        memberBase.setUpdateBy(copy.getUpdateBy());
        memberBaseMapper.insertSelective(memberBase);

        MemberLoginInfo memberLoginInfo = new MemberLoginInfo();

        memberLoginInfo.setId(leafIdFeign.getSegmentID("member"));
        memberLoginInfo.setMemberId(memberId);

        memberLoginInfo.setLoginType(registerRequest.getRegisterType());
        memberLoginInfo.setThirdSourceCode(registerRequest.getThirdSourceCode());

        memberLoginInfo.setLoginNameType(registerRequest.getRegisterNameType());

        memberLoginInfo.setLoginName(registerRequest.getRegisterName());
        //memberLoginInfo.setLoginPwdType();
        memberLoginInfo.setLoginPwd(registerRequest.getPassWord());
//        memberLoginInfo.setLoginPwdStrength();

        memberLoginInfoMapper.insertSelective(memberLoginInfo);
//        memberLoginInfo.setCreateDate(copy.getCreateDate());
//        memberLoginInfo.setCreateBy(copy.getCreateBy());
//        memberLoginInfo.setUpdateDate(copy.getUpdateDate());
//        memberLoginInfo.setUpdateBy(copy.getUpdateBy());


//        账户前置-开户
//        分布式事务

        registerRequest.setMemberId(memberId);
        registerAfter(registerRequest);

        return memberInfo;
    }


    public void addRegLog(RegisterRequest registerRequest) {
        MemberRegLog memberRegLog = new MemberRegLog();


        memberRegLogMapper.insertSelective(memberRegLog);
    }


}
