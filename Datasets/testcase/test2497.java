package test.refactoring.movemethod;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
// Target class with local inner classclass TargetClass {private String baseData;
public TargetClass(String baseData) {this.baseData = baseData;}
public class TargetInner {private int innerId;private String innerData;
public TargetInner(int innerId, String innerData) {this.innerId = innerId;this.innerData = innerData;// Local inner class in targetclass Validator {boolean isValid() {return innerId > 0 && innerData != null;}}if (!new Validator().isValid()) {throw new IllegalArgumentException("Invalid inner data");}}
public int getInnerId() {return innerId;}
public String getInnerData() {return innerData;}
public void setInnerData(String innerData) {this.innerData = innerData;}}
public String getBaseData() {return baseData;}}
// Abstract source class with static nested and member inner classesabstract class SourceClass {protected String outerData;
public SourceClass(String outerData) {this.outerData = outerData;}
// Static nested classstatic class SourceStaticNested {static void processInner(TargetClass.TargetInner inner) {inner.setInnerData(inner.getInnerData() + "_processed");}}
// Member inner class (depended on by method)class SourceInner {private String handlerData;
public SourceInner(String handlerData) {this.handlerData = handlerData;}
public void enhance(TargetClass.TargetInner inner) {inner.setInnerData(inner.getInnerData() + "_" + handlerData);}}
// Abstract method (called via method reference)protected abstract void validate(TargetClass.TargetInner inner) throws SQLException;
// Varargs method returning TargetClass typeTargetClass process(TargetClass.TargetInner... inners) {if (inners == null || inners.length == 0) {return new TargetClass("default");}
// Uses outer thisTargetClass resultTarget = new TargetClass(outerData + "_result");
// Variable callObject varCall = inners[0].getInnerData();
// Expression statementinners[0].setInnerData(inners[0].getInnerData() + "_modified");
// Depends on inner classSourceInner innerHandler = new SourceInner("enhancer");innerHandler.enhance(inners[0]);
// Requires try-catch (SQLException)try {// Array initialization with source abstract method referenceRunnable[] validators = {() -> { try { SourceClass::validate; } catch (SQLException e) {} },() -> { try { validate(inners[0]); } catch (SQLException e) {} }};validators[0].run();} catch (Exception e) {e.printStackTrace();}
// Switch caseint processedCount = 0;for (TargetClass.TargetInner inner : inners) {processedCount++;switch (inner.getInnerId() % 3) {case 0:SourceStaticNested.processInner(inner);break;case 1:inner.setInnerData(inner.getInnerData() + "_case1");break;default:inner.setInnerData(inner.getInnerData() + "_default");}}
// SQLExceptionif (processedCount < 0) {try {throw new SQLException("Negative processing count");} catch (SQLException e) {e.printStackTrace();}}
return resultTarget;}}
// Concrete implementation of abstract source classclass ConcreteSource extends SourceClass {public ConcreteSource(String outerData) {super(outerData);}
@Overrideprotected void validate(TargetClass.TargetInner inner) throws SQLException {if (inner.getInnerData().length() == 0) {throw new SQLException("Empty inner data");}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3201 {@Testpublic void testVarargsMethod() {SourceClass source = new ConcreteSource("source_data");TargetClass target = new TargetClass("base");TargetClass.TargetInner inner1 = target.new TargetInner(1, "data1");TargetClass.TargetInner inner2 = target.new TargetInner(2, "data2");
TargetClass result = source.process(inner1, inner2);assertEquals("source_data_result", result.getBaseData());assertEquals("data1_modified_enhancer_case1", inner1.getInnerData());}}