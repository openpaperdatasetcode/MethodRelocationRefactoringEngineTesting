package source;
import target.ProtectedTarget;
private class SourceClass {private int outerField;
public class SourceInnerRec {private ProtectedTarget targetParam;
private SourceInnerRec() {try {final Object obj = new Object();if (obj instanceof ProtectedTarget) {}
private int localVar = 1;this.targetParam = new ProtectedTarget();SourceClass.this.outerField;localVar++;} catch (Exception e) {}}
public void createLocalInner() {class LocalInner {}}
public void createAnonymousInner() {Runnable r = new Runnable() {public void run() {}};}}}
package target;
protected class ProtectedTarget {}