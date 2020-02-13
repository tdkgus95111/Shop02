package dbpkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ShopDAO extends DAOBase{
	private static ShopDAO instance = new ShopDAO();
	public static ShopDAO getInstance() {
		return instance;
	}
	private ShopDAO() {}
	// 전역변수
	private ResultSet rs = null;
	private int rss = 0;
	private PreparedStatement pstmt = null;
	private Connection conn = null;
	private String sql = null;
	
	//멤버 리스트 전부 출력 listMem.jsp
	public ArrayList<MemberVO> listMem() {
		ArrayList<MemberVO> list = null;
		try {
			conn = getConnection();
			sql = "select custno, custname, phone, address, to_char(joindate, 'YYYY-MM-DD'), grade, city from member_tbl_01";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<MemberVO>();
				do {
					MemberVO vo = new MemberVO();
					vo.setCustno(rs.getString(1));
					vo.setCustname(rs.getString(2));
					vo.setPhone(rs.getString(3));
					vo.setAddress(rs.getString(4));
					vo.setJoindate(rs.getString(5));
					if (rs.getString(6).equals("A")) {
						vo.setGrade("VIP");
					} else if (rs.getString(6).equals("B")) {
						vo.setGrade("일반");
					} else if (rs.getString(6).equals("C")) {
						vo.setGrade("직원");
					}
					vo.setCity(rs.getString(7));
					list.add(vo);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close() 메소드를 사용하여 인스턴스를 종료
			closeDBresource(rs, pstmt, conn);
		}
		return list;
	}
	
	//회원등록시 사용할 회원번호 생성 insertMem.jsp
	public String getCustno() {
		String custno = null;
		try {
			conn = getConnection();
			sql = "select max(custno)+1 from member_tbl_01 ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				custno = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBresource(rs, pstmt, conn);
		}
		return custno;
	}
	
	//회원등록 insertProMem.jsp
	public int insertMem(MemberVO vo) {
		try {
			conn = getConnection();
			sql = "insert into member_tbl_01 values (?, ?, ?, ?, to_date(?, 'YYYYMMdd'), ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getJoindate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
			rss = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBresource(pstmt, conn);
		}
		return rss;
	}
	
	//해당 회원 정보 읽기 updateMem.jsp
	public MemberVO getMem(String custno) {
		MemberVO vo = new MemberVO();
		try {
			conn = getConnection();
			sql = "select custno, custname, phone, address, to_char(joindate, 'YYYY-MM-dd'), grade, city "
					+ " from member_tbl_01 "
					+ " where custno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setCustno(rs.getString(1));
				vo.setCustname(rs.getString(2));
				vo.setPhone(rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setJoindate(rs.getString(5));
				vo.setGrade(rs.getString(6));
				vo.setCity(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBresource(rs, pstmt, conn);
		}
		return vo;
	}
	
	//회원정보 수정 updateProMem.jsp
	public int updateMem(MemberVO vo) {
		try {
			conn = getConnection();
			sql = "UPDATE member_tbl_01 SET custno = ?, custname = ?, phone = ?, address = ?, joindate = ?, grade = ?, city = ? "
					+ " where custno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getJoindate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
			pstmt.setString(8, vo.getCustno());
			rss = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBresource(pstmt, conn);
		}
		return rss;
	}
	
	//회원매출조회 listPrice.jsp
	public ArrayList<PriceVO> listPrice() {
		ArrayList<PriceVO> list = null;
		try {
			conn = getConnection();
			sql = "select me.custno, me.custname, me.grade, sum(mo.price) as total " + 
					" from member_tbl_01 me" + 
					" join money_tbl_01 mo" + 
					" on mo.custno = me.custno" + 
					" GROUP by me.custno, me.custname, me.grade" + 
					" order by total desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<PriceVO>();
				do {
					PriceVO vo = new PriceVO();
					vo.setCustno(rs.getString(1));
					vo.setCustname(rs.getString(2));
					if (rs.getString(3).equals("A")) {
						vo.setGrade("VIP");
					} else if (rs.getString(3).equals("B")) {
						vo.setGrade("일반");
					} else if (rs.getString(3).equals("C")) {
						vo.setGrade("직원");
					}
					vo.setTotal(rs.getString(4));
					list.add(vo);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBresource(rs, pstmt, conn);
		}
		return list;
	}
	
	//
	
	//
	
	//
}
