package com.jup.myapp.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data // getter, setter 지동으로 만들어줌, 컴파일 서명에
@Builder
@AllArgsConstructor
public class Account {
    private String ano;
    private String ownerName;
    private long balance;
    private long createTime;
}
