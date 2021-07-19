package com.callor.woo.service;

import java.util.List;

import com.callor.woo.model.WeatherDTO;

public interface SeasonService {

   public String selectSeason(List<WeatherDTO> today);
}