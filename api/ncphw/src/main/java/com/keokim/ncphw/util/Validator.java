package com.keokim.ncphw.util;

import com.keokim.ncphw.domain.CreateServerFormState;

public class Validator {

    public static boolean isValidServerName(String serverName) {
        if (serverName.length() < 3 || serverName.length() > 30) {
            return false;
        }

        if (!Character.isLowerCase(serverName.charAt(0))) {
            return false;
        }

        char lastChar = serverName.charAt(serverName.length() - 1);
        if (!Character.isLowerCase(lastChar) && !Character.isDigit(lastChar)) {
            return false;
        }

        for (char c : serverName.toCharArray()) {
            if (!Character.isLowerCase(c) && !Character.isDigit(c) && c != '-') {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidCreateServerFormState(CreateServerFormState formState) {
        if (formState.getServerName() == null) {
            return false;
        }
        if (formState.getVpcNo() == null) {
            return false;
        }
        if (formState.getAcgNo() == null) {
            return false;
        }
        if (formState.getProductCode() == null) {
            return false;
        }
        return formState.getSubnetNo() != null;
    }
}
