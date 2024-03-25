package org.example.lectureapplication.repository;

import org.example.lectureapplication.domain.ApplyLecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcLectureRepository implements LectureRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcLectureRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ApplyLecture save(String userId) {
        String sql = "INSERT INTO apply_lecture (USER_ID, APPLY_LECTURE_TIME, APPLY_LECTURE_STATUS ) VALUES (?, CURRENT_TIMESTAMP, ?)";
        jdbcTemplate.update(sql, userId, "신청 완료");

        // 저장된 레코드 조회
        return findApplyList(userId).orElse(null);
    }

    @Override
    public Optional<ApplyLecture> findApplyList(String userId) {
        String sql = "SELECT * FROM apply_lecture WHERE USER_ID = ?";
        List<ApplyLecture> applyLectures = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ApplyLecture.class), userId);

        return applyLectures.isEmpty() ? Optional.empty() : Optional.of(applyLectures.get(0));
    }

    @Override
    public List<ApplyLecture> findApplyListAll() {
        String sql = "SELECT * FROM apply_lecture";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ApplyLecture.class));
    }
}
