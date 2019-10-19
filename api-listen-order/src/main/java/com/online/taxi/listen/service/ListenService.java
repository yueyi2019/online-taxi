package com.online.taxi.listen.service;

import com.online.taxi.listen.response.PreGrabResponse;

/**
 * @author yueyi2019
 */
public interface ListenService {
    /**
     *
     * @param driverId
     * @return
     */
    public PreGrabResponse listen(int driverId);
}
