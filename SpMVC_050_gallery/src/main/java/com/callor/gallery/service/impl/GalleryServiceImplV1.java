package com.callor.gallery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.FileDTO;
import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.model.PageDTO;
import com.callor.gallery.persistance.ext.FileDao;
import com.callor.gallery.persistance.ext.GalleryDao;
import com.callor.gallery.service.FileService;
import com.callor.gallery.service.GalleryService;
import com.callor.gallery.service.PageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service("galleryServiceV1")
public class GalleryServiceImplV1 implements GalleryService {

	protected final GalleryDao gDao;
	protected final FileDao fDao;
	@Qualifier("fileServiceV2")
	protected final FileService fService;

	protected final PageService pageService;
	/*
	 * @Autowired가 설정된 변수, method, 객체 등을 만나면 Spring framework는 변수를 초기화, method를 실행핳여
	 * 또 변수 초기화 이미 생성되어 준비된 객체에 주입등을 수행한다
	 * 
	 */
	/*
	 * @Autowired public void create_table(GalleryDao gDao) {
	 * 
	 * Map<String, String> maps = new HashMap<String, String>();
	 * gDao.create_table(maps); fDao.create_table(maps);
	 * 
	 * 
	 * }
	 */

	@Override
	public int insert(GalleryDTO galleryDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void input(GalleryDTO gDTO, MultipartFile one_file, MultipartHttpServletRequest m_file) throws Exception {
		// TODO Auto-generated method stub

		// 대표이미지가 업로드 되면...
		// 이미지를 서버에 저장하고
		// 저장된 파일의 이름을 return 받기
		String strUUID = fService.fileUp(one_file);

		// DTO에 이미지 이름을 저장하기
		gDTO.setG_image(strUUID);

		// GalleryDTO에 담긴 데이터를 tbl_gallery table에 insert 하기
		// mapper에서 insert를 수해한 후 새로 생성된 g_seq 값을
		// selelctKey 하여 gDTO의 g_seq 변수에 담아놓은 상태이다

		gDao.insert(gDTO);

		log.debug("INSERT 후 seq {}", gDTO.getG_seq());

		// 갤러리 게시판 seq 값 파일들을 묶음으로 insert하기 위한 준비하기
		Long g_seq = gDTO.getG_seq();

		List<FileDTO> files = new ArrayList<FileDTO>();

		// 업로드된 멀티파일을 서버에 업로드하고
		// 원래 파일이름과 UUID가 첨가된 파일이름을 추출하여
		// FileDTO에 담고
		// 다시 List에 담아 놓는다
		List<MultipartFile> mFiles = m_file.getFiles("m_file");
		for (MultipartFile file : mFiles) {

			String fileOriginName = file.getOriginalFilename();
			String fileUUName = fService.fileUp(file);

			FileDTO fDTO = FileDTO.builder().file_gseq(g_seq).file_original(fileOriginName).file_upname(fileUUName)
					.build();

			files.add(fDTO);
		}
		log.debug("이미지 들 {}", files.toString());

		fDao.insertOrUpdateWithList(files);

	}

	@Override
	public List<GalleryDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub

		List<GalleryDTO> gList = gDao.selectAll();
		log.debug("갤러리 리스트 {}", gList.toString());
		return gList;
	}

	@Override
	public List<GalleryFilesDTO> findByIdGalleryFiles(Long g_seq) {
		// TODO Auto-generated method stub

		List<GalleryFilesDTO> gFList = gDao.findByIdGalleryFiles(g_seq);
		/*
		 * Dao로부터 select를 한 후 데이터 검증 하기 위해 사용하는 코드 gFList가 데이터가 조회되지 않아 null이 발생할 수 있다
		 */

		if (gFList != null && gFList.size() > 0) {
			log.debug(gFList.toString());
		} else {
			log.debug("조회된 데이터가 없음");
		}
		return gFList;

	}

	@Override
	public GalleryDTO findByIdGallery(Long g_seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Long g_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GalleryDTO> selectAllPage(int pageNum) throws Exception {
		// TODO Auto-generated method stub

		// 1 전체 데이터 SELECT 하기
		List<GalleryDTO> gListAll = gDao.selectAll();

		// 2 pageNum가 1이라면 list에서 0번째 요소 ~ 9번째 요소까지 추출하기
		// pageNum가 2라면 list에서 10번째 요소 ~ 19번째 요소까지 추출하기
		// pageNum가 3라면 list에서 20번째 요소 ~ 29번째 요소까지 추출하기

		int totalCount = gListAll.size();

		int start = (pageNum - 1) * 10;
		int end = pageNum * 10;

		if (pageNum * 10 > totalCount - 10) {
			end = totalCount;
			start = end - 10;
		}

		List<GalleryDTO> pageList = new ArrayList<>();
		for (int i = start; i < end; i++) {
			pageList.add(gListAll.get(i));
		}
		return pageList;
	}

	@Override
	public List<GalleryDTO> selectAllPage(int intPageNum, Model model) throws Exception {
		// TODO Auto-generated method stub

		List<GalleryDTO> galleryAll = gDao.selectAll();
		int totalListSize = galleryAll.size();
		
		PageDTO pageDTO = pageService.makePaination(totalListSize, intPageNum);
		List<GalleryDTO> pageList = new ArrayList<>();
		
		for(int i = pageDTO.getOffset(); i < pageDTO.getLimit();i++) {
			pageList.add(galleryAll.get(i));
		}
		
		model.addAttribute("PAGE_NAV", pageDTO);
		model.addAttribute("GALLERYS", pageList);
		
		
		/*
		List<GalleryDTO> pageList = this.selectAllPage(intPageNum);
		int galleryTotal = gDao.countAll();
		int totalPages = galleryTotal / 10;

		// 현재 선택된 page가 14 라면
		// page / 2를 하여 선택된 page 번호에서 값을 뺄셈하여 시작 값으로 설정
		// startPage = 7
		int startPage = (intPageNum - (10 / 2));
		int endPage = startPage + 10;

		PageDTO pageDTO = PageDTO.builder().totalPages(totalPages).startPage(startPage).endPage(endPage).build();
		model.addAttribute("PAGE_NAV", pageDTO);

		
		위 PageDTO 코드 두 줄로 대체 가능
		model.addAttribute("START_PAGE", startPage);
		model.addAttribute("END_PAGE",endPage);
		model.addAttribute("TOTAL_PAGE", totalPages);
		
		model.addAttribute("GALLERYS", pageList);
		 */
		return null;
	}

	@Override
	public List<GalleryDTO> findBySearchPage(int pageNum, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GalleryDTO> fineBySearchOderPage(int pageNum, String search, String column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int file_delete(Long g_seq) {
		// TODO Auto-generated method stub

		// 파일을 삭제하기 위하여 저장된 파일 정보를 SELECT 하기
		FileDTO fDTO = fDao.findById(g_seq);

		// 업로드되어 저장된 파일을 삭제
		int ret = fService.delete(fDTO.getFile_upname());

		if (ret > 0) {
			// tbl_files table에서 데이터를 삭제하기
			ret = fDao.delete(g_seq);
		}
		return ret;
	}

	@Override
	public List<GalleryDTO> findBySearchPage(String search_column, String search_text, int pageNum, Model model) {
		// TODO Auto-generated method stub
		
		List<GalleryDTO> galleryList = gDao.findBySearch(search_column, search_text);
		
		int totalListSize = galleryList.size();
		PageDTO pageDTO = pageService.makePaination(totalListSize, pageNum);
		
		List<GalleryDTO> pageList = new ArrayList<>();
		
		if(pageDTO == null) {
			model.addAttribute("GALLEYS", galleryList);
			return null;
		}
		
		for(int i = pageDTO.getOffset(); i< pageDTO.getLimit(); i++) {
			pageList.add(galleryList.get(i));
		}
		
				
		model.addAttribute("GALLERYS", pageList);
		
		return null;
	}

}
