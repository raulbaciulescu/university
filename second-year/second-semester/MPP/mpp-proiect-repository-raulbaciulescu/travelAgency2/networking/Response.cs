

namespace networking
{
    [Serializable]
    public class Response
    {
        public ResponseType type { get; set; }
        public Object data { get; set; }

        public Response()
        {
        }

        public override string ToString()
        {
            return "Response{" +
                   "type='" + type + '\'' +
                   ", data='" + data + '\'' +
                   '}';
        }
        public class Builder{
            private static Response response = new Response();

            public Builder Type(ResponseType type) {
                response.type = type;
                return this;
            }

            public Builder Data(Object data) {
                response.data = data;
                return this;
            }

            public Response Build() {
                return response;
            }
        }
    }    
}

