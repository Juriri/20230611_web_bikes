package com.map.member;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class mapMember {
    private String address;
    private double latitude; //위도
    private double longitude; //경도
}
