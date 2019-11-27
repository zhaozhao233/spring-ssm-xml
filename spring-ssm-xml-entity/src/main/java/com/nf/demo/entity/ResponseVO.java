package com.nf.demo.entity;

import lombok.Data;

@Data
public class ResponseVO {

    private String core;
    private String msg;
    private String data;


    public static final class ResponseVOBuilder {
        private String core;
        private String msg;
        private String data;

        private ResponseVOBuilder() {
        }

        public static ResponseVOBuilder aResponseVO() {
            return new ResponseVOBuilder();
        }

        public ResponseVOBuilder withCore(String core) {
            this.core = core;
            return this;
        }

        public ResponseVOBuilder withMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public ResponseVOBuilder withData(String data) {
            this.data = data;
            return this;
        }

        public ResponseVO build() {
            ResponseVO responseVO = new ResponseVO();
            responseVO.core = this.core;
            responseVO.msg = this.msg;
            responseVO.data = this.data;
            return responseVO;
        }
    }
}
