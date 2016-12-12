package com.snoweagle.console.service.system.generator.autocode;

import com.netfinworks.fc.channel.component.exception.ChannelException;
import com.netfinworks.fc.channel.component.model.autocode.ChannelInterface;
import com.netfinworks.fc.channel.component.model.autocode.ChannelInterfaceField;
import com.netfinworks.fc.channel.component.utils.StringHandler;

import java.util.List;

/**
 * <p>渠道Test生成器</p>
 * @author Wang Shaobo
 * @version $Id: ChannelClassGenerator.java, v 0.1 2014年11月22日 上午10:37:35 Wang Shaobo Exp $
 */
public class ChannelTestGenerator  extends ChannelGenerator {
    /**
     * @see com.netfinworks.fc.channel.component.basis.XMLConfig#initialize()
     */
    public ChannelTestGenerator(byte[] bytes) {
        super(bytes);
        SystemPrint4Imp("ChannelTestGenerator has initialized!");
    }

    public String test(String ifName) throws Exception {
        sbf_gen= new StringBuffer();
        ChannelInterface cf = interFaces.get(ifName);
        SystemPrint4Gen("@Test");
        SystemPrint4Gen("public void "+cf.getName() +"Test() {");
        SystemPrint4Gen(cf.getRequestClassName() + " request = new " + cf.getRequestClassName() + "();");
        SystemPrint4Gen("request.setFundChannelCode(\"\");");
        SystemPrint4Gen("request.setApiType(FundChannelApiType.);");
        SystemPrint4Gen("Map<String ,String > map = new HashMap<String, String>();");

        //本接口所有字段配置参数
        List<ChannelInterfaceField> fieldConf =  requestFields.get(ifName);
        
        //循环接口配置信息
        for(ChannelInterfaceField fc :fieldConf){
                String isNotNull =fc.getNotNull();
                if("true".equals(isNotNull)){//如果存在非空校验
                    StringBuffer sb = new StringBuffer();
                    String  locType = fc.getLocType();
                    String locName = fc.getLocName();
                    String isSecret = fc.getIsSecret();
                    
                    if("extension".equals(locType)){//取扩展字段值
                            sb.append("map.put(\"").append(locName).append("\",\"\");");
                    }else if("attribute".equals(locType)){//取固有属性值
                            sb.append("request.set").append(StringHandler.firstCharacterToUpper(locName)).append("(\"\");");
                    }else if("constants".equals(locType)){
                         //ignore
                    }else{
                            throw new ChannelException("无效locType！loctype:"+locType);
                    }
                    if("true".equals(isSecret)){
                        sb.append("//TODO uesService.encryptData()");
                    }
                    SystemPrint4Gen(sb.toString());
                }
        }
        
        
        SystemPrint4Gen("request.setExtension(map);");
        SystemPrint4Gen("LOG.warn(\"外部测试JSON :\"+JSON.toJSONString(request));");
        SystemPrint4Gen("ChannelFundResult result = .apply(request);");
        SystemPrint4Gen("LOG.warn(\"resultcode\": + result.getApiResultCode());");
        SystemPrint4Gen("LOG.warn(\"message\": + result.getApiResultMessage());");

        SystemPrint4Gen(cf.getDescription() + " TEST方法 执行完毕!");

        return sbf_gen.toString();
    }
    
}
