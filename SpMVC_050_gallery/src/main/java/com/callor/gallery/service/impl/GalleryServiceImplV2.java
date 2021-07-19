package com.callor.gallery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.callor.gallery.model.FileDTO;
import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.persistance.ext.FileDao;
import com.callor.gallery.persistance.ext.GalleryDao;
import com.callor.gallery.service.FileService;
import com.callor.gallery.service.PageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("galleryServiceV2")
public class GalleryServiceImplV2 extends GalleryServiceImplV1 {

	public GalleryServiceImplV2(GalleryDao gDao, FileDao fDao,  @Qualifier("fileServiceV2")FileService fService, PageService pageService) {
		super(gDao, fDao, fService, pageService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GalleryDTO findByIdGallery(Long g_seq) {
		// TODO Auto-generated method stub
		GalleryDTO gallery = gDao.findByIdGalleryFilesResultMap(g_seq);
		if (gallery != null) {
			log.debug("갤러리 데이터 {}", gallery.toString());
		}
		return gallery;

	}

	/*
	 * 첨부파일이 있는 게시물 삭제하기
	 * 1. 첨부파일을 제거한 후 
	 * 2. 게시물 데이터를 삭제
	 * 
	 * 첨부파일 뿐만 아니라 JOIN된 데이터가 또 있다.
	 * JOIN 데이터에 첨부파일 정보가 또 있다
	 * 
	 * 1. JOIN 된 데이터의 첨부파일을 삭제 한 후
	 * 2. JOIN된 데이터를 삭제하고
	 * 3. 본문(Gallery)의 첨부파일을 삭제하고
	 * 4. 본문을 삭제
	 */
	@Override
	public int delete(Long g_seq) {
		// TODO Auto-generated method stub

		// Gallery 데이터와 fileList 데이터가 같이 포함된 데이터
		GalleryDTO gaDTO = gDao.findByIdGalleryFilesResultMap(g_seq);
		if (gaDTO == null) {
			return 0;
		}

		List<FileDTO> fileList = gaDTO.getFileList();
		for (FileDTO file : fileList) {

			// 첨부파일 삭제

			String attFileName = file.getFile_upname();
			int ret = fService.delete(attFileName);

			// 데이터 한개씩 삭제
			if (ret > 0) {
				fDao.delete(file.getFile_seq());
			}

		}

		// 본문 첨부파일 삭제
		String imgFileName = gaDTO.getG_image();
		int ret = fService.delete(imgFileName);

		if (ret > 0) {
			// 본문 데이터 삭제
			gDao.delete(g_seq);
		} else {
			log.debug("파일 삭제 실패로 데이터 삭제 하지 않음");
		}
		return 0;
	}

}
