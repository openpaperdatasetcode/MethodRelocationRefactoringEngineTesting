package source;
import target.TargetInterface;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
interface SourceInterface {@CustomAnnotationdefault Object normalMethod(TargetInterface targetParam) {try {class LocalInner {}LocalInner local = new LocalInner();
Runnable r = new Runnable() {public void run() {}};
synchronized (targetParam) {TargetInterface.StaticNested nested = new TargetInterface.StaticNested();return nested;}} catch (Exception e) {return null;}}}
package target;
public interface TargetInterface {static class StaticNested {}void normalMethod();}