package com.map.service;

import com.dagn.service.dagnService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class mapService {
    //싱글톤 인스턴스
    private static mapService service = new mapService();
    public static mapService getService(){
        return service;
    }
    public void getCourse() {
        try {
            // 웹 페이지 가져오기
            String url = "https://www.strava.com/segments/9506401";  // 코스 정보가 있는 웹사이트의 URL을 입력하세요

            Document document = Jsoup.connect(url).get();

            // 코스 정보 추출

            Elements courseElements = document.select("div.map-container map-large leaflet-container leaflet-touch leaflet-retina leaflet-safari leaflet-fade-anim leaflet-grab leaflet-touch-drag");  // 웹사이트에서 코스 정보가 있는 요소를 선택하세요

            // 코스 정보를 처리하고 출력 또는 지도에 표시하는 코드 추가
            for (Element course : courseElements) {
                // 코스의 위치 정보 추출
                String latitude = course.attr("data-latitude");  // 코스의 위도 정보가 있는 속성을 입력하세요
                String longitude = course.attr("data-longitude");  // 코스의 경도 정보가 있는 속성을 입력하세요

                // 마커 생성 및 처리 코드 추가
                // ...

                // 지도에 마커 추가하는 코드 추가
                // ...
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
