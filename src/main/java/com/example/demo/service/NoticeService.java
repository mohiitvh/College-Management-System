package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.NoticeEntity;
import com.example.demo.repo.NoticeRepo;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepo repo;


    public NoticeEntity save(
            NoticeEntity notice){

        return repo.save(notice);

    }


    public List<NoticeEntity>
    getAllActive(){

        return repo
                .findByActiveTrueOrderByNoticeDateDesc();

    }


    public List<NoticeEntity>
    getAll(){

        return repo.findAll();

    }


    public NoticeEntity
    findById(Long id){

        return repo
                .findById(id)
                .orElse(null);

    }
    public void delete(Long id){

        repo.deleteById(id);

    }

}