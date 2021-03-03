package com.demon.common;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "responseVo")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String info;
    private String status;
    private String fileid;
    private String server;
    private Object data;
    private Throwable throwable;

    public ResponseVo() {
    	this.status="0";
    }
    
	public String getInfo() {
        if (StringUtils.isEmpty(info)){
            if("0".equals(status)){
                info = "执行成功";
            }else{
                info = "执行失败";
            }
        }
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public ResponseVo successData(Object data){
        this.data = data;
        this.status = "0";
        this.info = "获取成功";
        return this;
    }
	
	public ResponseVo successServer(String server){
		this.server = server;
		this.status = "0";
		this.info = "获取成功";
		return this;
	}

    public ResponseVo successData(){
        this.status = "0";
        this.info = "获取成功";
        return this;
    }

    public ResponseVo failData(){
        this.status = "-1";
        this.info = "操作失败";
        return this;
    }

    public ResponseVo failureMsg(String msg){
        this.info = msg;
        this.status = "-1";
        return this;
    }
    
    public ResponseVo failureMsg(String msg,Throwable throwable) {
		this.throwable = throwable;    	
		return failureMsg(msg);
    }

    public ResponseVo failureMsg(Throwable throwable) {
		this.throwable = throwable;  
		this.status="-1";
		this.info=throwable.getMessage();
		return this;
    }
    
    public ResponseVo successData(String fileid,String server){
        this.status = "0";
        this.info = "获取成功";
        this.fileid=fileid;
        this.server=server;
        return this;
    }
    
    public ResponseVo successDataYsp(String fileid){
        this.status = "0";
        this.info = "获取成功";
        this.fileid=fileid;
        return this;
    }


	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}
