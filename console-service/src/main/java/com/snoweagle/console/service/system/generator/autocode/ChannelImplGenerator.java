package com.snoweagle.console.service.system.generator.autocode;

import com.netfinworks.fc.channel.component.exception.ChannelException;
import com.netfinworks.fc.channel.component.model.autocode.ChannelInterface;
import com.netfinworks.fc.channel.component.model.autocode.ChannelInterfaceField;
import com.netfinworks.fc.channel.component.utils.StringHandler;

import java.util.List;

/**
 * <p></p>
 * @author Wang Shaobo
 * @version $Id: ChannelClassGenerator.java, v 0.1 2014年11月22日 上午10:37:35 Wang Shaobo Exp $
 */
public class ChannelImplGenerator  extends ChannelGenerator {

    /**
     * @see com.netfinworks.fc.channel.component.basis.XMLConfig#initialize()
     */
    public ChannelImplGenerator(byte[] bytes) {
        super(bytes);
        SystemPrint4Imp("ChannelMangeHandler has initialized!");

    }

    public void validateLocalRequest(String ifName)  {
        ChannelInterface cf = interFaces.get(ifName);

        SystemPrint4Gen("public void validate(" + cf.getRequestClassName() + " request) {");

        //本接口所有字段配置参数
        List<ChannelInterfaceField> fieldConf =  requestFields.get(ifName);
        
        SystemPrint4Gen("Map<String, String> map = request.getExtension();");
        
        //循环接口配置信息
        for(ChannelInterfaceField fc :fieldConf){
                if("true".equals(fc.getNotNull())){//如果存在非空校验
                    StringBuffer sb = new StringBuffer();
                    String  locType = fc.getLocType();
                    String locName = fc.getLocName();
                    String dsc = fc.getDescription()+"不能为空";
                    String isSecret = fc.getIsSecret();
                    sb.append("AssertUtil.assertNotBlank(");
                    
                    if("extension".equals(locType)){//取扩展字段值
                            sb.append("map.get(\"").append(locName).append("\"),\"").append(dsc).append("\");");
                    }else if("attribute".equals(locType)){//取固有属性值
                            sb.append("request.get").append(StringHandler.firstCharacterToUpper(locName)).append("(),\"").append(dsc).append("\");");
                    }else{
                            throw new ChannelException("无效locType！loctype:"+locType);
                    }
                    if("true".equals(isSecret)){
                        sb.append("//TODO uesService.getDataByTicket()");
                    }
                    SystemPrint4Gen(sb.toString());
                }
        }
        SystemPrint4Gen("}");
        SystemPrint4Gen(cf.getDescription() + " 本地校验方法 执行完毕!");
    }
    
    public void valueMap(String ifName) {
        ChannelInterface cf = interFaces.get(ifName);
        SystemPrint4Gen("public void valueMap(" + cf.getRequestClassName() + " request) {");
        SystemPrint4Gen("Map<String, String> map = request.getExtension();");
        //本接口所有字段配置参数
        List<ChannelInterfaceField> fieldConf =  requestFields.get(ifName);
        //循环接口配置信息
        for(ChannelInterfaceField fc :fieldConf){
                if("true".equals(fc.getNotNull())){//如果存在非空校验
                        StringBuffer sb = new StringBuffer();
                        String locType = fc.getLocType();
                        String locName = fc.getLocName();
                        String isValMap = fc.getIsValMap();
                        String isSecret = fc.getIsSecret();
                        sb.append("//TODO ");
                        if("extension".equals(locType)){//取扩展字段值
                                sb.append("map.put(\"").append(locName).append("\",\"\")");
                        }else if("attribute".equals(locType)){//取固有属性值
                                sb.append("request.set").append(StringHandler.firstCharacterToUpper(locName)).append("(\"\"),\"");
                        }else{
                            throw new ChannelException("无效locType！loctype:"+locType);
                        }
                        if("true".equals(isValMap)){
                                SystemPrint4Gen(sb.toString() + "//TODO Util.getPropertiesData(properties.getProperty(),)");
                        }
                        if("true".equals(isSecret)){
                            SystemPrint4Gen(sb.toString() + " //TODO uesService.getDataByTicket()");
                        }
                }
        }
        SystemPrint4Gen("}");
        SystemPrint4Gen(cf.getDescription() + " 代码映射转化方法 执行完毕!");


    }
    public void  convertRemoteRequest(String ifName)   {
            ChannelInterface cf = interFaces.get(ifName);
            SystemPrint4Gen("private Map<String, String> convertRemoteRequest(" + cf.getRequestClassName() + " request) {");
            
            //本接口所有字段配置参数
            List<ChannelInterfaceField> fieldConf =  requestFields.get(ifName);
            
            SystemPrint4Gen("Map<String, String> ext = request.getExtension();");
             SystemPrint4Gen("Map<String, String> remote = new HashMap<String, String>();");
        //循环接口配置信息
            for(ChannelInterfaceField fc :fieldConf){
                    StringBuffer sb = new StringBuffer();
                    if("true".equals(fc.getIsSend())){//需要发送
                            String  locType = fc.getLocType();
                            String  chnName = fc.getChannelName();
                            String locName = fc.getLocName();
                            
                            sb.append("remote.put(\"");
                            if("extension".equals(locType)){//取扩展字段值
                                sb.append(chnName).append("\",ext.get(\"").append(locName).append("\"));");
                            }else if("attribute".equals(locType)){//取固有属性值
                                sb.append(chnName).append("\",request.get").append(StringHandler.firstCharacterToUpper(locName)).append("());");
                            }else if("constants".equals(locType)){//取固有属性值
                                sb.append(chnName).append("\",cc.getValue(\"").append(locName).append("\"));");
                            }else{
                                 throw new ChannelException("无效locType！loctype:"+locType);
                            }
                            SystemPrint4Gen(sb.toString());
                    }
            }
            SystemPrint4Gen("return remote;");
            SystemPrint4Gen("}");
            SystemPrint4Gen(cf.getDescription() + " 远程报文转化方法 执行完毕!");

    }
    
    public void  validateRemoteResponse(String ifName)  {
        ChannelInterface cf = interFaces.get(ifName);
        SystemPrint4Gen("private void validateRemoteResponse(Map<String, String> map) {");

        //本接口所有字段配置参数
        List<ChannelInterfaceField> fieldConf =  responseFields.get(ifName);
        
        //循环接口配置信息
        for(ChannelInterfaceField fc :fieldConf){
                if("true".equals(fc.getNotNull())){//如果存在非空校验
                    StringBuffer sb = new StringBuffer();
                    String channelName = fc.getChannelName();
                    String dsc = fc.getDescription();
                    String isSecret = fc.getIsSecret();
                    sb.append("AssertUtil.assertNotBlank(");
                    sb.append("map.get(\"").append(channelName).append("\"),\"").append(dsc).append("\");");
                    if("true".equals(isSecret)){
                        sb.append("//TODO uesService.encryptData()");
                    }
                    System.out.println(sb.toString());
                }
        }
        SystemPrint4Gen("}");
        SystemPrint4Gen(cf.getDescription() + " 远程校验方法 执行完毕!");
    }
    
    public void  convertLocalResponse(String ifName) {
        ChannelInterface cf = interFaces.get(ifName);
        SystemPrint4Gen("private String convertLocalResponse(Map<String, String> resp) {");
        SystemPrint4Gen("Map<String,String> map = new HashMap<String,String>();");
        //本接口所有字段配置参数
        List<ChannelInterfaceField> fieldConf =  responseFields.get(ifName);
        
      //循环接口配置信息
        for(ChannelInterfaceField fc :fieldConf){
                StringBuffer sb = new StringBuffer();
                if("true".equals(fc.getIsSend())){//需要发送
                        String  locType = fc.getLocType();
                        String  chnName = fc.getChannelName();
                        String locName = fc.getLocName();
                        String isValMap = fc.getIsValMap();
                        String isSecret = fc.getIsSecret();
                        if("extension".equals(locType)){//取扩展字段值
                            sb.append("map.put(\"").append(locName).append("\",resp.get(\"").append(chnName).append("\"));");
                        }else if("attribute".equals(locType)){//取固有属性值
                               //ignore
                            sb.append("result.set").append(StringHandler.firstCharacterToUpper(locName)).append("(resp.get(\"").append(chnName).append("\"));");
                        }else{
                                throw new ChannelException("无效locType！loctype:"+locType);
                        }
                        if("true".equals(isSecret)){
                            sb.append("//TODO isSecret uesService.getDataByTicket()");
                        }
                        if("true".equals(isValMap)){
                            sb.append("//TODO   isValMap valueMapping.findFsnCode(ValueMappingConstant.");
                        }
                    SystemPrint4Gen(sb.toString());
                }
        }
        SystemPrint4Gen("return MapUtil.mapToJson(map);");
        SystemPrint4Gen("}");
        SystemPrint4Gen(cf.getDescription() + " 对内转化方法 执行完毕!");
    }
    public String generatorAll(String  ifName){
        sbf_gen= new StringBuffer();
        validateLocalRequest(ifName);
        valueMap(ifName) ;
        convertRemoteRequest(ifName) ;
        validateRemoteResponse(ifName) ;
        convertLocalResponse(ifName);
        return sbf_gen.toString();
    }
}
