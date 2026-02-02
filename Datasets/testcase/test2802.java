package com.source;
import com.target.TargetRecord;import java.util.List;import java.util.ArrayList;
protected record SourceRecord<T>(T data) {class SourceInner {class SourceInnerRec {/**
Method Javadoc for instance method to test Move Method refactoring
@param target TargetRecord's inner recursive class parameter*/void methodToMove(TargetRecord.TargetInner.TargetInnerRec target) {// Instance method from inner_class in while loopint count = 0;while (count < 1) {TargetRecord.TargetInner.TargetInnerRec result = target.thisInnerMethod(target);count++;}
// Enhanced for statementList<String> targetList = target.getTargetList();for (String str : targetList) {// Variable callstr.toString();}
// If statement + requires_try_catchif (targetList.isEmpty()) {try {throw new IllegalArgumentException("Target list is empty");} catch (IllegalArgumentException e) {e.printStackTrace();}}}}}
public void createLocalInner() {class LocalInnerSource {}}}
package com.target;
import java.util.List;import java.util.ArrayList;
public record TargetRecord(String value) {class TargetInner {class TargetInnerRec {private final List<String> targetField = new ArrayList<>(List.of("targetData"));
public TargetInnerRec thisInnerMethod(TargetInnerRec param) {return param;}
public List<String> getTargetList() {return targetField;}}}}