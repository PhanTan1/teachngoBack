package be.teachngo.repository;

import be.teachngo.data.Mode;
import be.teachngo.data.ModeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeRepository extends JpaRepository<Mode, Long> {


    Mode findByModeName(ModeType mode);
}
