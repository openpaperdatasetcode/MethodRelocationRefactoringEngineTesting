package test;
import java.util.List;
public class SourceClass {public record SourceInnerRec(String data) {public TargetClass.TargetInnerRec varargsMethod(TargetClass.TargetInnerRec... targetRecs) {try {TargetClass.TargetInnerRec firstRec = targetRecs[0];String varCallResult = firstRec.content();
if (varCallResult.equals(TargetClass.STATIC_FIELD)) {return firstRec;}return new TargetClass.TargetInnerRec("default");} catch (ArrayIndexOutOfBoundsException e) {return new TargetClass.TargetInnerRec("error");}}}}
class TargetClass {public static final String STATIC_FIELD = "targetStaticValue";
public record TargetInnerRec(String content) {}}