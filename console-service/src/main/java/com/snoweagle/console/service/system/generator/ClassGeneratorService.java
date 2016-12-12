package com.snoweagle.console.service.system.generator;


import com.snoweagle.console.service.system.generator.autocode.ChannelImplGenerator;
import com.snoweagle.console.service.system.generator.autocode.ChannelMockGenerator;
import com.snoweagle.console.service.system.generator.autocode.ChannelTestGenerator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClassGeneratorService {

    public Map initializeFile(byte[] bytes)throws Exception{
        Map map = new HashMap();
        map.put("I_generator", new ChannelImplGenerator(bytes));
        map.put("T_generator", new ChannelTestGenerator(bytes));
        map.put("M_generator", new ChannelMockGenerator(bytes));
        return map;
    }

    public Map generatorClass (Map gen , String ifName)throws Exception{
        ChannelImplGenerator I_generator = (ChannelImplGenerator)gen.get("I_generator");
        ChannelTestGenerator T_generator = (ChannelTestGenerator)gen.get("T_generator");
        ChannelMockGenerator M_generator = (ChannelMockGenerator)gen.get("M_generator");

        Map map = new HashMap();
        map.put("I" ,I_generator.generatorAll(ifName));
        map.put("T" ,T_generator.test(ifName));
        map.put("M" ,M_generator.service(ifName));

        return map;
    }
}
