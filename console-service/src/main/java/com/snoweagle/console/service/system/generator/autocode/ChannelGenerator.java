package com.snoweagle.console.service.system.generator.autocode;

import com.netfinworks.fc.channel.component.basis.XMLConfig;
import com.netfinworks.fc.channel.component.model.autocode.ChannelInterFaceRequest;
import com.netfinworks.fc.channel.component.model.autocode.ChannelInterFaceResponse;
import com.netfinworks.fc.channel.component.model.autocode.ChannelInterface;
import com.netfinworks.fc.channel.component.model.autocode.ChannelInterfaceField;
import org.dom4j.tree.DefaultElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>渠道生成类父类，所有需要读取xml配置的继承继承，构造函数传入xml路径</p>
 * @author Wang Shaobo
 * @version $Id: ChannelClassGenerator.java, v 0.1 2014年11月22日 上午10:37:35 Wang Shaobo Exp $
 */
public class ChannelGenerator  extends XMLConfig {
    /*初始化输出*/
    public StringBuffer sbf_imp = new StringBuffer();
    /*生成class输出*/
    public StringBuffer sbf_gen = new StringBuffer();
        /**object's config  */
      public Map<String, ChannelInterface> interFaces = new ConcurrentHashMap<String, ChannelInterface>();
      /** config of request*/
      public Map<String, List<ChannelInterfaceField>> requestFields = new ConcurrentHashMap<String, List<ChannelInterfaceField>>();
      /**config of response */
      public  Map<String, List<ChannelInterfaceField>> responseFields = new ConcurrentHashMap<String, List<ChannelInterfaceField>>();
    

    public ChannelGenerator(byte[] bytes) {
        setBytes(bytes);
        SystemPrint4Imp("read inferface config file...");
        super.initializeBytes();
        SystemPrint4Imp("initialized interface config class...");
        this.initializeChannelInterfaces();
        SystemPrint4Imp("initialized the  class of request and response...");
        this.initializeInferFaceFields();

        SystemPrint4Imp("ChannelMangeHandler has initialized!");

    }
   /**
    *  channel xml  convertd to class
    */
     private void initializeChannelInterfaces(){
         
         List<DefaultElement>  ifList = getConfig().selectNodes("/channel/interfaces/interface");
         //所有接口集合
         for(DefaultElement e :ifList){
             ChannelInterface interFace = new ChannelInterface();
             interFace.setName( e.attributeValue("name"));
             interFace.setDescription( e.attributeValue("description"));
             interFace.setRequestClassName(e.attributeValue("requestClassName"));
                 
                     //所有request属性集合
                     ChannelInterFaceRequest request = new ChannelInterFaceRequest();
                     List<DefaultElement > efdReqList =  e.element("request").elements();
                         List<ChannelInterfaceField> reqFields = new ArrayList<ChannelInterfaceField>();
                         for(DefaultElement rf :efdReqList){
                             ChannelInterfaceField field = new ChannelInterfaceField(rf.attributeValue("channelName"),
                                 rf.attributeValue("description"), rf.attributeValue("isSend"),rf.attributeValue("notNull"), rf.attributeValue("locName"), rf.attributeValue("locType"),rf.attributeValue("isValMap") ,rf.attributeValue("isSecret"));
                                 reqFields.add(field);
                         }
                         request.setFields(reqFields);
                     
                     ChannelInterFaceResponse response = new ChannelInterFaceResponse();
                     //所有response属性集合
                     List<DefaultElement > efdRespList =  e.element("response").elements();
                     List<ChannelInterfaceField> respFields = new ArrayList<ChannelInterfaceField>();
                     for(DefaultElement rf :efdRespList){
                         ChannelInterfaceField field = new ChannelInterfaceField(rf.attributeValue("channelName"),
                             rf.attributeValue("description"), rf.attributeValue("isSend"),rf.attributeValue("notNull"), rf.attributeValue("locName"), rf.attributeValue("locType"),rf.attributeValue("isValMap") ,rf.attributeValue("isSecret"));
                             respFields.add(field);
                     }
                     response.setFields(respFields);
             
             interFace.setRequest(request);
             interFace.setResponse(response);
             interFaces.put(interFace.getName(),interFace);
             SystemPrint4Imp("[ " + interFace.getDescription() + " ] initialization is complete！");
         }
     }
     /**
      * initialize the map of request and response 
      */
       private void initializeInferFaceFields(){
           for (Entry<String, ChannelInterface> chnConf : interFaces.entrySet()) {
               requestFields.put(chnConf.getKey() , chnConf.getValue().getRequest().getFields());
               responseFields.put(chnConf.getKey() , chnConf.getValue().getResponse().getFields());
              }
       }

    void SystemPrint4Gen(String str){
        sbf_gen.append(str).append("\n<br>");
    }
    void SystemPrint4Imp(String str){
        sbf_imp.append(str).append("\n<br>");
    }
}
