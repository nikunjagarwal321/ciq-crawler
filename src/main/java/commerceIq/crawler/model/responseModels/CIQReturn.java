
package commerceIq.crawler.model.responseModels;

/**
  * Common Response Model for all CIQ Requests
  */
public class CIQReturn<T> {
    private String error;
    private T returnValue;
    private double responseTimeSeconds;



    public CIQReturn() {
    }

    public CIQReturn(String error, T returnValue, double responseTimeSeconds) {
        this.error = error;
        this.returnValue = returnValue;
        this.responseTimeSeconds = responseTimeSeconds;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getReturnValue() {
        return this.returnValue;
    }

    public void setReturnValue(T returnValue) {
        this.returnValue = returnValue;
    }

    public double getResponseTimeSeconds() {
        return this.responseTimeSeconds;
    }

    public void setResponseTimeSeconds(double responseTimeSeconds) {
        this.responseTimeSeconds = responseTimeSeconds;
    }

    @Override
    public String toString() {
        return "{" +
            " error='" + getError() + "'" +
            ", returnValue='" + getReturnValue() + "'" +
            ", responseTimeSeconds='" + getResponseTimeSeconds() + "'" +
            "}";
    }
    
   
}
