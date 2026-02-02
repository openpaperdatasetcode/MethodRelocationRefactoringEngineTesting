package same.pkg;
/**
Target class with local inner class for business logic*/class TargetClass {private int id;
public TargetClass(int id) {this.id = id;}
public int getId() {return id;}
public void processLocal() {class LocalInner {void validateId() {if (id < 0) {throw new IllegalArgumentException("Invalid ID");}}}new LocalInner().validateId();}}
public class SourceClass permits SubSourceClass {static class StaticNested {static boolean isTargetValid(TargetClass target) {return target != null && target.getId() >= 0;}}
class MemberInner {void preProcessTarget(TargetClass target) {target.processLocal();}}
/**
Recursively processes TargetClass instance to find valid target.
@param target Parameter of TargetClass to process
@param depth Recursion depth limit
@return Valid TargetClass instance or null if depth exceeds limit*/public TargetClass recursiveProcess(TargetClass target, int depth) {if (depth <= 0) {return null;}
new MemberInner().preProcessTarget(target);int targetId = target.getId();TargetClass result = target;
while (depth > 0) {switch (targetId % 3) {case 0:result = this.recursiveProcess(new TargetClass(targetId - 1), depth - 1);break;case 1:if (StaticNested.isTargetValid(result)) {depth = 0;break;}targetId--;continue;default:targetId++;continue;}depth--;}
return result;}}
class SubSourceClass extends SourceClass {}