package be.teachngo.repository;

import be.teachngo.data.Mode;
import be.teachngo.data.ModeType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
class ModeRepositoryTest {

    @Autowired
    private ModeRepository modeRepository;

    @Test
    void findByModeName() {

        for (ModeType type : ModeType.values()) {
            Mode mode = new Mode();
            mode.setModeName(type);
            modeRepository.save(mode);
        }

        for (ModeType type : ModeType.values()) {
            assertEquals(type, modeRepository.findByModeName(type).getModeName());
        }
    }
}