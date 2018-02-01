package com.dminc.dts.budget.tracker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DomainRangeResult {

    private String domainKey;
    private List<String> rangeKeys = new ArrayList<String>();
    private List<Map<String,String>> data = new ArrayList<Map<String,String>>();

    public String getDomainKey() {
        return domainKey;
    }
    
    public List<String> getRangeKeys() {
        return new ArrayList<String>(rangeKeys);
    }
    
    public List<Map<String,String>> getData() {
        return data;
    }
    
    public static class Builder {
        private DomainRangeResult result;
        private List<DomainRangeValue> values;
        private String domainKey;

        public Builder(List<DomainRangeValue> values) {
            this.values = values;
        }
        
        public Builder domainKey(String domainKey) {
            this.domainKey = domainKey;
            return this;
        }

        public DomainRangeResult build() {

//          TURN THIS:
//            {domain: 2018-01-29, range: Jim Garlick, value: 40}, {domain: 2018-01-29, range: Boshu Liu, value: 40}
//          INTO THIS:
//            {week: 2018-01-29, Jim Garlick: 40, Boshu Liu: 40}

            Map<String, List<DomainRangeValue>> domainMap = buildDomainRangeValueMap();

            DomainRangeResult result = new DomainRangeResult();

            result.data = buildDomainLists(domainMap);
            
            result.rangeKeys = getDistinctObjectValues(values, DomainRangeValue::getRange);
            
            result.domainKey = domainKey;
            
            return result;
        }
        
        private Map<String, List<DomainRangeValue>> buildDomainRangeValueMap() {

            // Holy Shit!
            return values.stream().collect(Collectors.groupingBy(v -> v.getDomain()));
        }

        private List<Map<String,String>> buildDomainLists(Map<String, List<DomainRangeValue>> domainMap) {
            List<Map<String,String>> domainList = new ArrayList<>();

            
            // Look at groupingBy
            for (String key : domainMap.keySet()) {              
                Map<String,String> mapEntry = domainMap.get(key).stream()
                        .collect(Collectors.toMap(DomainRangeValue::getRange, DomainRangeValue::getValue));                

                mapEntry.put(domainKey, key);
                
                domainList.add(mapEntry);
            }
            
            return domainList;
        }
        
        private <T,R> List<R> getDistinctObjectValues(List<T> values, Function<? super T,R> function) {
            return values.stream()
                    .map(function)
                    .distinct()
                    .collect(Collectors.toList());
        }
    }
    
}
