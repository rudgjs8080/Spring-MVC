package com.callor.woo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.woo.model.WeatherDTO;
import com.callor.woo.service.SeasonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("seasonService")
public class SeasonServiceImplV1 implements SeasonService{

   @Override
   public String selectSeason(List<WeatherDTO> weather) {
      // TODO 최고기온을 이용하여 계절찾기

      for(int i = 0; i < weather.size(); i++){
         
         if(weather.get(i).getCategory().equals("낮 최고기온")) {
            String value = weather.get(i).getFcstValue();
            String tp = value.substring(0, value.length()-2);
            float temp = Float.valueOf(tp);
            String season = null;
            
            log.debug("최고기온 {}", temp);
            
            if(temp <= 8)season="겨울";
            else if(temp <= 22)season="봄";
            else season="여름";
            log.debug("계절 {}", season);
            return season;
         }
      }
      return null;
   }
}