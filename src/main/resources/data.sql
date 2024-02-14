ALTER TABLE rewind_question DROP INDEX unique_content;
ALTER TABLE rewind_question ADD UNIQUE KEY unique_content (content);
INSERT IGNORE INTO rewind_question (content)
VALUES
    ('오늘 여행에서, 어떤 순간이 가장 기억에 남아?'),
    ('오늘 여행에서, 조금 아쉬웠던 기억이 있을까?')
;