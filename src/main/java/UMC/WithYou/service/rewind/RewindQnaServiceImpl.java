package UMC.WithYou.service.rewind;

import UMC.WithYou.domain.rewind.RewindQna;
import UMC.WithYou.repository.rewind.RewindQnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewindQnaServiceImpl implements RewindQnaService{

    private final RewindQnaRepository rewindQnaRepository;
    @Override
    public boolean checkQnaIdExist(Long qnaId) {
        Optional<RewindQna> rewindQuestion = rewindQnaRepository.findById(qnaId);
        return rewindQuestion.isPresent();
    }
}
