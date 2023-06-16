namespace networking
{    
    [Serializable]
    public class Request
    {
        public RequestType type { get; set; }
        public Object data { get; set; }

        public override string ToString()
        {
            return "Request{" +
                   "type='" + type + '\'' +
                   ", data='" + data + '\'' +
                   '}';
        }
        public class Builder{
            private static Request request = new Request();

            public Builder Type(RequestType type) {
                request.type = type;
                return this;
            }

            public Builder Data(Object data) {
                request.data = data;
                return this;
            }

            public Request Build() {
                return request;
            }
        }
    }    
}

