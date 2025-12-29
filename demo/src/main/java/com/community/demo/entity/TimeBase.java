package com.community.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 등록일, 수정일만 따로 관리하는 슈퍼 클래스 // 모든 entity가 사용
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class TimeBase {

    @CreatedDate
    @Column(name="reg_date", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="mod_date")
    private LocalDateTime modDate;

}
