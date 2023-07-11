package com.map.service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import com.map.member.mapMember;
public class mapService {
    //싱글톤 인스턴스
    private static mapService service = new mapService();
    public static mapService getService(){
        return service;
    }


    public ArrayList<String> getRoute(double startLat, double startLon, double endLat, double endLon){

        String API_KEY = "5b3ce3597851110001cf62487ec22f1f0b0b4aae9c637f739b903835";
        String url = "https://api.openrouteservice.org/v2/directions/cycling-regular?api_key="+API_KEY+"&start="+startLon + ',' + startLat + "&end=" + endLon + ',' + endLat;

        ArrayList<String>list = new ArrayList<>();
        //출발지 위도 경도
        list.add(Double.toString(startLat));
        list.add(Double.toString(startLon));
        list.add(Double.toString(endLat));
        list.add(Double.toString(endLon));
        list.add(url);

        return list;
    }

    public ArrayList<Double> getCourse(String province, int num){
        double startLat = 0, startLon =0 ;
        double endLat = 0, endLon =0 ;
        ArrayList<Double> list = new ArrayList<>();

        switch(province) {
            case "서울" :
                if(num == 1) { //세빛섬 - 두물머리
                    startLat = 37.512182;
                    startLon = 126.994815;

                    endLat =  37.534913;
                    endLon = 127.316748;
                } else {
                    if(num == 2) {

                    } else {
                      if(num == 3) {

                      } else if(num == 4) {

                      } else if(num == 5) {

                      } else {

                      }
                    }
                }
                break;
            case "경기" :
                break;
            case "강원" :
                break;
            case "충북" :
                break;
            case "충남" :
                break;
            case "전남" :
                break;
            case "제주" :
                break;
        }

        list.add(startLat);
        list.add(startLon);
        list.add(endLat);
        list.add(endLon);
        return list;
    }

    public mapMember getInfoByAddress(String address){
        mapMember member = new mapMember();
        try {
            // OpenRouteService API 요청 URL 생성
            String urlStr = "https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf62487ec22f1f0b0b4aae9c637f739b903835&text=" + address;

            // HTTP 요청 보내기
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 응답 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 응답 JSON 파싱
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray features = jsonObject.getJSONArray("features");
            if (features.length() > 0) {
                JSONObject firstFeature = features.getJSONObject(0);
                JSONArray coordinates = firstFeature.getJSONObject("geometry").getJSONArray("coordinates");
                double longitude = coordinates.getDouble(0);
                double latitude = coordinates.getDouble(1);

                member.setAddress(address);

                member.setLatitude(latitude);

                member.setLongitude(longitude);

            } else {
                System.out.println("주소를 찾을 수 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return member;
    }

    public ArrayList<mapMember> getLaLoByAddress() {
        ArrayList<String> list_str = new ArrayList<>();

        list_str.add("세빛섬");
        list_str.add("서울웨이브");
        list_str.add("두물머리");

        ArrayList<mapMember> list = new ArrayList<>();
        for(String s : list_str) {
            list.add(getInfoByAddress(s));
        }

        return list;
    }
}
