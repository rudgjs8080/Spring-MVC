package com.callor.gallery.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.model.MemberVO;
import com.callor.gallery.persistance.ext.GalleryDao;
import com.callor.gallery.service.GalleryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {

	@Qualifier("galleryServiceV2")
	protected final GalleryService gService;

	// localhost:8080/rootPath/gallery/ 또는
	// localhost:8080/rootPath/gellery 로 요청했을 때
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
			@RequestParam(value="search_column", required = false, defaultValue = "NONE") String search_column,
			@RequestParam(value="search_text", required = false, defaultValue = "NONE") String search_text,
			Model model) throws Exception {

		int intPageNum = Integer.valueOf(pageNum);

		if (intPageNum > 0) {
			model.addAttribute("PAGE_NUM", intPageNum);
		}

		List<GalleryDTO> galleryPageList = gService.selectAllPage(intPageNum, model);
		model.addAttribute("GALLERYS", galleryPageList);

		// search_column, search_text를 사용하여 조건검색 (서비스한테 model을 넘겨주고 있다)
		
		gService.findBySearchPage(search_column, search_text, intPageNum, model);
		
		model.addAttribute("BODY", "GA-LIST");
		return "home";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Model model, HttpSession session) {

		MemberVO mVO = (MemberVO) session.getAttribute("MEMBER");
		if (mVO == null) {
			return "redirect:/member/login";
		}

		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");

		String curDate = sd.format(date);
		String curTime = st.format(date);

		GalleryDTO gDTO = GalleryDTO.builder().g_date(curDate).g_time(curTime).g_writer("Key").build();

		model.addAttribute("CMD", gDTO);
		model.addAttribute("BODY", "GA-INPUT");

		return "home";
	}
	/*
	 * BODY를 왜 사용하는지?
	 * 
	 * builder로 설계하는 이유
	 * 필요한 DTO만 만들어서 사용할 수 있다
	 * 위에서 보면 날짜 시간 작성자만 만들어서 사용하는 것을 볼 수 있다
	 * 
	 */

	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public String input(GalleryDTO gDTO, MultipartFile one_file, MultipartHttpServletRequest m_file, Model model)
			throws Exception {

		log.debug("갤러리 정보 {}", gDTO.toString());
		log.debug("싱글파일 {}", one_file.getOriginalFilename());
		log.debug("멀티파일 {}", m_file.getContentType());

		gService.input(gDTO, one_file, m_file);

		return "redirect:/gallery";
	}

	@RequestMapping(value = "/detail/{seq}", method = RequestMethod.GET)
	public String detail(@PathVariable("seq") String seq, Model model) {

		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return "redirect:/gallery";
		}

		List<GalleryFilesDTO> gfList = gService.findByIdGalleryFiles(g_seq);

		model.addAttribute("GFLIST", gfList);
		model.addAttribute("BODY", "GA-DETAIL");

		return "home";

	}

	@RequestMapping(value = "/detail2/{seq}", method = RequestMethod.GET)
	public String detail(@PathVariable("seq") String seq, Model model, HttpSession session) {

		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("갤러리 ID 값 오류");
			return "redirect:/";
		}

		GalleryDTO galleryDTO = gService.findByIdGallery(g_seq);
		model.addAttribute("GALLERY", galleryDTO);
		model.addAttribute("BODY", "GA-DETAIL-V2");

		return "home";
	}

	/*
	 * 첨부파일이 있는 게시물의 삭제
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("g_seq") String seq, HttpSession session) {

		// 삭제를 요구하면
		// 1. 로그인이 되었나 확인
		//MemberVO memVO = (MemberVO) session.getAttribute("MEMBER");
		//if(memVO == null) {
		//	return "redirect:/member/login";
		//}

		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("갤러리 SEQ 오류");
			return "redirect:/gallery";
		}

		int ret = gService.delete(g_seq);
		return "redirect:/gallery";
	}

	@ResponseBody
	@RequestMapping(value = "/file/delete/{seq}", method = RequestMethod.GET)
	public String file_delete(@PathVariable("seq") String seq) {

		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			return "FAIL_SEQ";
		}

		int ret = gService.file_delete(g_seq);

		if (ret > 0)
			return "OK";
		else
			return "FAIL";
	}

}
