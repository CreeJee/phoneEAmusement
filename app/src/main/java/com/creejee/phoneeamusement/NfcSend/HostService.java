package com.creejee.phoneeamusement.NfcSend;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

/**
 * Created by user on 2016-12-25.
 */
public final class HostService extends HostApduService {
    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        return new byte[]{0x00};
    }
    @Override
    public void onDeactivated(int reason) {

    }
}