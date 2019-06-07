package com.dotcom.nlcatering.Response.CustomerResponse;

public class MolliePaymentApi {


    /**
     * resource : payment
     * id : tr_7UhSN1zuXS
     * mode : test
     * createdAt : 2018-03-20T09:13:37+00:00
     * amount : {"value":"10.00","currency":"EUR"}
     * description : Order #12345
     * method : null
     * metadata : {"order_id":"12345"}
     * status : open
     * isCancelable : false
     * expiresAt : 2018-03-20T09:28:37+00:00
     * details : null
     * profileId : pfl_QkEhN94Ba
     * sequenceType : oneoff
     * redirectUrl : https://webshop.example.org/order/12345/
     * webhookUrl : https://webshop.example.org/payments/webhook/
     * _links : {"self":{"href":"https://api.mollie.com/v2/payments/tr_7UhSN1zuXS","type":"application/json"},"checkout":{"href":"https://www.mollie.com/payscreen/select-method/7UhSN1zuXS","type":"text/html"},"documentation":{"href":"https://docs.mollie.com/reference/v2/payments-api/create-payment","type":"text/html"}}
     */

    private String resource;
    private String id;
    private String mode;
    private String createdAt;
    private AmountBean amount;
    private String description;
    private Object method;
    private MetadataBean metadata;
    private String status;
    private boolean isCancelable;
    private String expiresAt;
    private Object details;
    private String profileId;
    private String sequenceType;
    private String redirectUrl;
    private String webhookUrl;
    private LinksBean _links;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public AmountBean getAmount() {
        return amount;
    }

    public void setAmount(AmountBean amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getMethod() {
        return method;
    }

    public void setMethod(Object method) {
        this.method = method;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsCancelable() {
        return isCancelable;
    }

    public void setIsCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getSequenceType() {
        return sequenceType;
    }

    public void setSequenceType(String sequenceType) {
        this.sequenceType = sequenceType;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public LinksBean get_links() {
        return _links;
    }

    public void set_links(LinksBean _links) {
        this._links = _links;
    }

    public static class AmountBean {
        /**
         * value : 10.00
         * currency : EUR
         */

        private String value;
        private String currency;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }

    public static class MetadataBean {
        /**
         * order_id : 12345
         */

        private String order_id;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }

    public static class LinksBean {
        /**
         * self : {"href":"https://api.mollie.com/v2/payments/tr_7UhSN1zuXS","type":"application/json"}
         * checkout : {"href":"https://www.mollie.com/payscreen/select-method/7UhSN1zuXS","type":"text/html"}
         * documentation : {"href":"https://docs.mollie.com/reference/v2/payments-api/create-payment","type":"text/html"}
         */

        private SelfBean self;
        private CheckoutBean checkout;
        private DocumentationBean documentation;

        public SelfBean getSelf() {
            return self;
        }

        public void setSelf(SelfBean self) {
            this.self = self;
        }

        public CheckoutBean getCheckout() {
            return checkout;
        }

        public void setCheckout(CheckoutBean checkout) {
            this.checkout = checkout;
        }

        public DocumentationBean getDocumentation() {
            return documentation;
        }

        public void setDocumentation(DocumentationBean documentation) {
            this.documentation = documentation;
        }

        public static class SelfBean {
            /**
             * href : https://api.mollie.com/v2/payments/tr_7UhSN1zuXS
             * type : application/json
             */

            private String href;
            private String type;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class CheckoutBean {
            /**
             * href : https://www.mollie.com/payscreen/select-method/7UhSN1zuXS
             * type : text/html
             */

            private String href;
            private String type;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class DocumentationBean {
            /**
             * href : https://docs.mollie.com/reference/v2/payments-api/create-payment
             * type : text/html
             */

            private String href;
            private String type;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
