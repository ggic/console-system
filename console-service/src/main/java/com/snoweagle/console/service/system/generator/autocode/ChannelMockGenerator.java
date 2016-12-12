package com.snoweagle.console.service.system.generator.autocode;

import com.netfinworks.fc.channel.component.model.autocode.ChannelInterface;
import com.netfinworks.fc.channel.component.model.autocode.ChannelInterfaceField;

import java.util.List;

/**
 * <p>渠道mock生成器</p>
 * @author Wang Shaobo
 * @version $Id: ChannelMockClassGenerator.java, v 0.1 2014年11月22日 上午10:37:35 Wang Shaobo Exp $
 */
public class ChannelMockGenerator  extends ChannelGenerator {
    /**
     * @see com.netfinworks.fc.channel.component.basis.XMLConfig#initialize()
     */
        public ChannelMockGenerator(byte[] bytes) {
            super(bytes);

            SystemPrint4Imp("ChannelMockClassGenerator has initialized!");
    }

    public String service(String ifName) throws Exception {
        sbf_gen= new StringBuffer();
        ChannelInterface cf = interFaces.get(ifName);

            //本接口所有字段配置参数
            List<ChannelInterfaceField> fieldConf =  responseFields.get(ifName);
            
          //循环接口配置信息
            for(ChannelInterfaceField fc :fieldConf){
                    StringBuffer sb = new StringBuffer();
                    if("true".equals(fc.getIsSend())){//需要发送
                            String  chnName = fc.getChannelName();
                            String desc = fc.getDescription();
                            SystemPrint4Gen("//" + desc);
                            sb.append("data.put(\"").append(chnName).append("\",\"\");");
                            SystemPrint4Gen(sb.toString());
                    }
            }

            SystemPrint4Gen(cf.getDescription() + " mock service 执行完毕!");


        return sbf_gen.toString();
    }
}
