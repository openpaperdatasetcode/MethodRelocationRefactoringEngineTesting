package same.pkg;
abstract class TargetClass {protected String targetField;
public abstract String processData();}
private abstract class SourceClass {private String sourceField = "source_data";
static class SourceStaticNested {static boolean isValid(TargetClass target) {return target != null && target.targetField != null;}}
public Object handleTargets(TargetClass... targetParams) {int index = 0;while (index < targetParams.length) {TargetClass target = targetParams[index];if (!SourceStaticNested.isValid(target)) {index++;continue;}
synchronized (target) {try {String processed = this.getProcessedString(target);target.targetField = processed + "_updated";return target.targetField;} catch (Exception e) {e.printStackTrace();return null;}}}return null;}
private String getProcessedString(TargetClass target) {TargetClass anonTarget = new TargetClass() {@Overridepublic String processData() {return sourceField + "_" + target.targetField;}};return anonTarget.processData();}
@Overridepublic abstract Object handleTargets(TargetClass... targetParams);}