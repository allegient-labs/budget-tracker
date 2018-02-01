package com.dminc.dts.budget.tracker.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DomainRangeResultTest {
    private static final String DOMAIN_KEY = "key";
    
    public DomainRangeResult buildResult() {
        
        List<DomainRangeValue> values = new ArrayList<DomainRangeValue>() {{
            add(new DomainRangeValue.Builder("a", "b", "c").build());
            add(new DomainRangeValue.Builder("c", "e", "f").build());
            add(new DomainRangeValue.Builder("a", "h", "i").build());
            add(new DomainRangeValue.Builder("name", "class", "value").build());
        }};
        
        return new DomainRangeResult.Builder(values).domainKey(DOMAIN_KEY).build();
    }
    
    @Test
    public void testDomainRangeResultDomainKey() {
        
        DomainRangeResult result = buildResult();
        
        
        assertThat(DOMAIN_KEY).isEqualTo(result.getDomainKey());
    }

    @Test
    public void testDomainRangeResultGets() {
        
        DomainRangeResult result = buildResult();
        System.out.println(result.getRangeKeys().toString());
        System.out.println(result.getData().toString());
        //Working on this
        assertThat(DOMAIN_KEY).isEqualTo(result.getDomainKey());
    }


}
