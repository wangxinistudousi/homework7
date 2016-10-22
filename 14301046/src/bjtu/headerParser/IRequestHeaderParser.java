package bjtu.headerParser;

import bjtu.header.RequestHeader;

public interface IRequestHeaderParser {
	public RequestHeader parse(String txt) throws Exception;  
}
