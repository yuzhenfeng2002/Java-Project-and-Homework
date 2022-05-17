DELIMITER $$
CREATE Trigger judge BEFORE INSERT ON answer FOR EACH ROW
BEGIN
    DECLARE right_answer TEXT;
    DECLARE exercise_type INT;
    SELECT answer, type INTO right_answer, exercise_type FROM exercise WHERE exercise.id=NEW.exercise_id;
    IF ISNULL(right_answer) != 1 AND LENGTH(TRIM(right_answer)) != 0 THEN
        IF exercise_type = 1 OR exercise_type = 2 THEN
            IF right_answer = NEW.answer THEN
                SET NEW.is_right = 100;
            ELSE
                SET NEW.is_right = 0;
            END IF;
        END IF;
    END IF;
END
DELIMITER ;