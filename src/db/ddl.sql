-- ----------------------------
--  users
-- ----------------------------
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       provider VARCHAR(50) NOT NULL COMMENT 'google/naver',
                       email VARCHAR(320) NOT NULL UNIQUE COMMENT '소셜로그인에서 제공되는 이메일',
                       name VARCHAR(100) NOT NULL COMMENT '소셜로그인에서 제공되는 이름',
                       nickname VARCHAR(50) NOT NULL COMMENT '첫 로그인 시에는 name과 동일',
                       image_url VARCHAR(500),
                       status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT 'active/blocked/deleted',
                       last_login_at TIMESTAMP NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL,
                       deleted_at TIMESTAMP NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  question_types
-- ----------------------------
CREATE TABLE question_types (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(100) UNIQUE,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  questions
-- ----------------------------
CREATE TABLE questions (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           question_type_id BIGINT,
                           question TEXT NOT NULL,
                           answer TEXT NOT NULL,
                           created_at TIMESTAMP NOT NULL,
                           updated_at TIMESTAMP NOT NULL,
                           day INT NOT NULL,
                           CONSTRAINT fk_questions_question_type_id
                               FOREIGN KEY (question_type_id) REFERENCES question_types(id)
                                   ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  user_questions
-- ----------------------------
CREATE TABLE user_questions (
                                user_id BIGINT NOT NULL,
                                question_id BIGINT NOT NULL,
                                bookmark BOOLEAN NOT NULL DEFAULT FALSE,
                                status VARCHAR(20) NOT NULL DEFAULT '' COMMENT 'in_progress/correct/wrong',
                                created_at TIMESTAMP NOT NULL COMMENT '질문 생성 시간',
                                updated_at TIMESTAMP NOT NULL COMMENT '질문 답변 시간',
                                PRIMARY KEY (user_id, question_id),
                                CONSTRAINT fk_user_questions_user_id
                                    FOREIGN KEY (user_id) REFERENCES users(id)
                                        ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT fk_user_questions_question_id
                                    FOREIGN KEY (question_id) REFERENCES questions(id)
                                        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  user_question_attempts
-- ----------------------------
CREATE TABLE user_question_attempts (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        user_id BIGINT NOT NULL,
                                        question_id BIGINT NOT NULL,
                                        attempt_no INT NOT NULL DEFAULT 1 COMMENT '같은 문제에 대한 사용자의 N번째 시도',
                                        status VARCHAR(20) NOT NULL DEFAULT 'in_progress' COMMENT 'in_progress/correct/wrong',
                                        final_user_answer TEXT COMMENT '사용자가 최종 제출한 답',
                                        final_ai_feedback TEXT COMMENT 'AI가 요약/최종 피드백으로 제공한 텍스트',
                                        final_ai_summary TEXT COMMENT '전체 대화의 압축 요약',
                                        created_at TIMESTAMP NOT NULL,
                                        updated_at TIMESTAMP NOT NULL,
                                        CONSTRAINT fk_uqa_user_id FOREIGN KEY (user_id)
                                            REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                        CONSTRAINT fk_uqa_question_id FOREIGN KEY (question_id)
                                            REFERENCES questions(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  attempt_messages
-- ----------------------------
CREATE TABLE attempt_messages (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  attempt_id BIGINT NOT NULL,
                                  turn_no INT NOT NULL COMMENT '시도 내 메시지 순번(1부터)',
                                  role VARCHAR(20) NOT NULL COMMENT 'user/assistant',
                                  content TEXT NOT NULL COMMENT '메시지 원문',
                                  status VARCHAR(20) NOT NULL COMMENT 'assistant/correct/wrong',
                                  created_at TIMESTAMP NOT NULL,
                                  CONSTRAINT fk_attempt_messages_attempt_id
                                      FOREIGN KEY (attempt_id) REFERENCES user_question_attempts(id)
                                          ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  jobs
-- ----------------------------
CREATE TABLE jobs (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  user_job_interests
-- ----------------------------
CREATE TABLE user_job_interests (
                                    user_id BIGINT NOT NULL,
                                    job_id BIGINT NOT NULL,
                                    created_at TIMESTAMP NOT NULL,
                                    updated_at TIMESTAMP NOT NULL,
                                    PRIMARY KEY (user_id, job_id),
                                    CONSTRAINT fk_user_job_user_id FOREIGN KEY (user_id)
                                        REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                    CONSTRAINT fk_user_job_job_id FOREIGN KEY (job_id)
                                        REFERENCES jobs(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ----------------------------
--  skills
-- ----------------------------
CREATE TABLE skills (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  user_skill_interests
-- ----------------------------
CREATE TABLE user_skill_interests (
                                      user_id BIGINT NOT NULL,
                                      skill_id BIGINT NOT NULL,
                                      created_at TIMESTAMP NOT NULL,
                                      updated_at TIMESTAMP NOT NULL,
                                      PRIMARY KEY (user_id, type_id),
                                      CONSTRAINT fk_user_skill_user_id FOREIGN KEY (user_id)
                                          REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                      CONSTRAINT fk_user_skill_skill_id FOREIGN KEY (skill_id)
                                          REFERENCES skills(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  study_groups
-- ----------------------------
CREATE TABLE study_groups (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              leader_id BIGINT,
                              name VARCHAR(100),
                              description TEXT,
                              visibility VARCHAR(20) DEFAULT 'public' COMMENT 'public/private',
                              max_members INT,
                              created_at TIMESTAMP NOT NULL,
                              updated_at TIMESTAMP NOT NULL,
                              deleted_at TIMESTAMP NULL,
                              CONSTRAINT fk_study_groups_leader_id
                                  FOREIGN KEY (leader_id) REFERENCES users(id)
                                      ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  study_group_members
-- ----------------------------
CREATE TABLE study_group_members (
                                     study_group_id BIGINT NOT NULL,
                                     user_id BIGINT NOT NULL,
                                     role VARCHAR(20) DEFAULT 'member' COMMENT 'leader/member',
                                     joined_at TIMESTAMP NOT NULL,
                                     PRIMARY KEY (study_group_id, user_id),
                                     CONSTRAINT fk_study_member_group_id FOREIGN KEY (study_group_id)
                                         REFERENCES study_groups(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                     CONSTRAINT fk_study_member_user_id FOREIGN KEY (user_id)
                                         REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;