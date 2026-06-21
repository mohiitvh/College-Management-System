package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.NoticeEntity;

@Repository
public interface NoticeRepo
extends JpaRepository<NoticeEntity, Long> {

    List<NoticeEntity>
    findByActiveTrueOrderByNoticeDateDesc();

}