package UMC.WithYou.common.apiPayload.exception.handler;

import UMC.WithYou.common.apiPayload.code.BaseErrorCode;
import UMC.WithYou.common.apiPayload.exception.GeneralException;

public class CommonErrorHandler extends GeneralException {

    public CommonErrorHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
