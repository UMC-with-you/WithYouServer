package UMC.WithYou.service.rewind;

import UMC.WithYou.domain.rewind.RewindQuestion;
import UMC.WithYou.repository.rewind.RewindQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewindQuestionQueryServiceImpl implements RewindQuestionQueryService{

    private final RewindQuestionRepository rewindQuestionRepository;

    @Override
    public Boolean checkQuestionIdExist(Long questionId) {
        Optional<RewindQuestion> rewindQuestion = rewindQuestionRepository.findById(questionId);
        return rewindQuestion.isPresent();
    }
}
