package test;
strictfp class SourceClass {// Anonymous inner class (source_feature)private Runnable anonInner = new Runnable() {@Overridepublic void run() {}};
// Member inner class (source_inner)public class SourceInner {default Object methodToMove(TargetClass target) {// Variable call + contains target parameter (per_condition)target.toString();TargetClass.Inner.InnerRec innerRec = target.new Inner().new InnerRec();Object result = null;
// Switch statementswitch (innerRec.getLevel()) {case 1:result = processLevel1(innerRec);break;case 2:result = processLevel2(innerRec);break;default:result = processDefault(innerRec);}
// Return statementreturn result;}
// if/else conditions with inner_class instance method callsprivate TargetClass.Inner.InnerRec processLevel1(TargetClass.Inner.InnerRec rec) {if (rec.getValue() > 10) {rec.updateValue(rec.getValue() * 2);} else {rec.updateValue(rec.getValue() + 5);}return rec;}
private TargetClass.Inner.InnerRec processLevel2(TargetClass.Inner.InnerRec rec) {if (rec.isValid()) {rec.setStatus("processed");} else {rec.setStatus("invalid");}return rec;}
private TargetClass.Inner.InnerRec processDefault(TargetClass.Inner.InnerRec rec) {rec.reset();return rec;}}}
strictfp class TargetClass {public class Inner {public class InnerRec {private int value = 8; // Source contains target's field (per_condition)private int level = 2;private String status = "init";
// 3 inner_class instance methods for method_featurepublic void updateValue(int val) {this.value = val;}
public void setStatus(String status) {this.status = status;}
public void reset() {this.value = 0;this.status = "reset";}
// Getter methodspublic int getValue() {return value;}
public int getLevel() {return level;}
public String getStatus() {return status;}
public boolean isValid() {return value > 0;}
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {}new TargetLocalInner();}}}
// Static code blocks with others_class method callstatic {OthersClass.processTarget(new TargetClass());}}
class OthersClass {// others_class instance method (ClassName.methodName(arguments))default int processTarget(TargetClass target) {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass.Inner.InnerRec rec = (TargetClass.Inner.InnerRec) inner.methodToMove(target);return rec.getValue();}}