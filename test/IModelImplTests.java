import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.model.IModel;
import cs3500.animator.model.IModelImpl;
import cs3500.animator.model.IModelShape;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IMotionImpl;
import cs3500.animator.model.ShapeType;
import cs3500.animator.util.AnimationBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the methods of IModel and IModelImpl, its implementation.
 */
public class IModelImplTests {

  IModel model1;
  AnimationBuilder<IModelImpl> builder;

  /**
   * Sets up a blank model without any shapes.
   */
  @Before
  public void setUp() {
    this.model1 = new IModelImpl();
    builder = IModelImpl.builder();
  }

  // Tests for IModelImpl#printAnimations()

  /**
   * Tests IModelImpl#printAnimations() with a model that has no IShapes in it.
   */
  @Test
  public void testPrintEmpty() {
    assertEquals("", this.model1.printAnimations());
  }

  /**
   * Tests IModelImpl#printAnimations() with a model that has 1 (rectangle) IModelShape in it,
   * without any motions in the shape.
   */
  @Test
  public void testPrintEmptyRect() {
    model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    assertEquals("shape R rectangle", this.model1.printAnimations());
  }

  /**
   * Tests IModelImpl#printAnimations() with a model that has 1 (ellipse) IModelShape in it, without
   * any motions in the shape.
   */
  @Test
  public void testPrintEmptyEll() {
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);

    assertEquals("shape C ellipse", model1.printAnimations());
  }

  /**
   * Tests for printing the animation of a model with 1 shape in it (no motions).
   */
  @Test
  public void testPrintWith1Shape() {
    assertEquals("", this.model1.printAnimations());
    this.model1.addShape("R", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);
    assertEquals("shape R rectangle", this.model1.printAnimations());
  }

  /**
   * Tests for printing the animation of a model with 2 shapes in it (no motions).
   */
  @Test
  public void testPrintWith2Shapes() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);

    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);

    assertEquals(
            "shape R rectangle\n\n"
                    + "shape C ellipse",
            this.model1.printAnimations());
  }

  /**
   * Tests for printing the animation of a model with 3 shapes in it (no motions).
   */
  @Test
  public void testPrintWith3Shapes() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addShape("S", ShapeType.RECTANGLE, 4, 5.9, 6.8, 1000, Color.RED);

    assertEquals(
            "shape R rectangle\n\n"
                    + "shape C ellipse\n\n"
                    + "shape S rectangle",
            this.model1.printAnimations());
  }

  /**
   * Tests to make sure the correct output is generated when you add 1 motion into a model with 3
   * shapes (none of which have motions).
   */
  @Test
  public void testPrintAdd1Motion() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);

    assertEquals(
            "shape R rectangle\n"
                    + "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n\n"
                    + "shape C ellipse\n\n"
                    + "shape S rectangle",
            this.model1.printAnimations());
  }

  /**
   * Tests to make sure the correct output is generated when you add 2 motions into a model with 3
   * shapes (none of which have motions).
   */
  @Test
  public void testPrintAdd2Motions() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);

    this.model1.addMotion("C",
            20, 30, 40, 0, 0, Color.ORANGE,
            30, 45, 60, 0, 0, Color.ORANGE);

    assertEquals(
            "shape R rectangle\n"
                    + "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n\n"
                    + "shape C ellipse\n"
                    + "motion C 20 30.0 40.0 0.0 0.0 255 200 0\t30 45.0 60.0 0.0 0.0 255 200 0\n\n"
                    + "shape S rectangle",
            this.model1.printAnimations());
  }

  /**
   * Tests to make sure the correct output is generated when you add 3 motions into a model with 3
   * shapes (none of which have motions).
   */
  @Test
  public void testPrintAdd3Motions() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);
    this.model1.addMotion("C",
            20, 30, 40, 0, 0, Color.ORANGE,
            30, 45, 60, 0, 0, Color.ORANGE);

    this.model1.addMotion("R",
            10, 30, 40, 40, 60, Color.CYAN,
            30, 50, 60, 80, 120, Color.GREEN);

    assertEquals(
            "shape R rectangle\n" +
                    "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n" +
                    "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t30 50.0 60.0 80.0 120.0 0 255 0\n" +
                    "\n" +
                    "shape C ellipse\n" +
                    "motion C 20 30.0 40.0 0.0 0.0 255 200 0\t30 45.0 60.0 0.0 0.0 255 200 0\n" +
                    "\n" +
                    "shape S rectangle",
            this.model1.printAnimations());
  }

  /**
   * Tests to make sure that no gaps are allowed when adding multiple motions at once
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionsWithGaps() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);
    this.model1.addMotion("C",
            20, 30, 40, 0, 0, Color.ORANGE,
            30, 45, 60, 0, 0, Color.ORANGE);
    this.model1.addMotion("R",
            20, 50, 60, 80, 120, Color.CYAN,
            30, 50, 60, 80, 120, Color.GREEN);
    this.model1.addMotion("S",
            10, 30, 40, 10, 10, Color.RED,
            25, 15, 20, 10, 20, Color.RED);
    this.model1.addMotion("S",
            35, 15, 20, 6, 7, Color.RED,
            45, 15, 20, 6, 7, Color.YELLOW);
    this.model1.addMotion("C",
            10, 30, 40, 10, 20, Color.ORANGE,
            15, 30, 40, 0, 0, Color.ORANGE);
    this.model1.addMotion("S",
            30, 15, 20, 10, 10, Color.RED,
            35, 15, 20, 6, 7, Color.RED);

    this.model1.addMotion("C",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 10, 20, Color.ORANGE);

    assertEquals(
            "shape R rectangle\n"
                    + "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n"
                    + "motion R 20 50.0 60.0 80.0 120.0 0 255 255\t30 50.0 60.0 80.0 120.0 0 255 0\n\n"
                    + "shape C ellipse\n"
                    + "motion C 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 10.0 20.0 255 200 0\n"
                    + "motion C 10 30.0 40.0 10.0 20.0 255 200 0\t15 30.0 40.0 0.0 0.0 255 200 0\n"
                    + "motion C 20 30.0 40.0 0.0 0.0 255 200 0\t30 45.0 60.0 0.0 0.0 255 200 0\n\n"
                    + "shape S rectangle\n"
                    + "motion S 10 30.0 40.0 10.0 10.0 255 0 0\t25 15.0 20.0 10.0 20.0 255 0 0\n"
                    + "motion S 30 15.0 20.0 10.0 10.0 255 0 0\t35 15.0 20.0 6.0 7.0 255 0 0\n"
                    + "motion S 35 15.0 20.0 6.0 7.0 255 0 0\t45 15.0 20.0 6.0 7.0 255 255 0",
            this.model1.printAnimations());
  }

  /**
   * Tests to make sure the correct output is generated when you add all of the shapes first, and
   * then the motions.
   */
  @Test
  public void testPrintShapesThenMotions() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            10, 30, 40, 40, 60, Color.CYAN,
            15, 45, 60, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            15, 45, 60, 40, 60, Color.CYAN,
            30, 50, 60, 80, 120, Color.GREEN);

    this.model1.addMotion("C",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 10, 20, Color.ORANGE);
    this.model1.addMotion("C",
            10, 30, 40, 10, 20, Color.ORANGE,
            15, 30, 40, 0, 0, Color.ORANGE);
    this.model1.addMotion("C",
            15, 30, 40, 0, 0, Color.ORANGE,
            30, 45, 60, 0, 0, Color.ORANGE);

    this.model1.addMotion("S",
            10, 30, 40, 10, 10, Color.RED,
            25, 15, 20, 10, 20, Color.RED);
    this.model1.addMotion("S",
            25, 15, 20, 10, 20, Color.RED,
            35, 15, 20, 6, 7, Color.RED);
    this.model1.addMotion("S",
            35, 15, 20, 6, 7, Color.RED,
            45, 15, 20, 6, 7, Color.YELLOW);

    assertEquals(
            "shape R rectangle\n" +
                    "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n" +
                    "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t15 45.0 60.0 40.0 60.0 0 255 255\n" +
                    "motion R 15 45.0 60.0 40.0 60.0 0 255 255\t30 50.0 60.0 80.0 120.0 0 255 0\n" +
                    "\n" +
                    "shape C ellipse\n" +
                    "motion C 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 10.0 20.0 255 200 0\n" +
                    "motion C 10 30.0 40.0 10.0 20.0 255 200 0\t15 30.0 40.0 0.0 0.0 255 200 0\n" +
                    "motion C 15 30.0 40.0 0.0 0.0 255 200 0\t30 45.0 60.0 0.0 0.0 255 200 0\n" +
                    "\n" +
                    "shape S rectangle\n" +
                    "motion S 10 30.0 40.0 10.0 10.0 255 0 0\t25 15.0 20.0 10.0 20.0 255 0 0\n" +
                    "motion S 25 15.0 20.0 10.0 20.0 255 0 0\t35 15.0 20.0 6.0 7.0 255 0 0\n" +
                    "motion S 35 15.0 20.0 6.0 7.0 255 0 0\t45 15.0 20.0 6.0 7.0 255 255 0",
            this.model1.printAnimations());
  }

  /**
   * Tests that an error is thrown when motions are added out of order. Same motions are added as
   * above test, but in an invalid order.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPrintShapesThenMotions1() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    this.model1.addMotion("S",
            30, 15, 20, 10, 10, Color.RED,
            35, 15, 20, 6, 7, Color.RED);
    this.model1.addMotion("R",
            10, 30, 40, 40, 60, Color.CYAN,
            15, 45, 60, 40, 60, Color.CYAN);
    this.model1.addMotion("C",
            20, 30, 40, 0, 0, Color.ORANGE,
            30, 45, 60, 0, 0, Color.ORANGE);
    this.model1.addMotion("R",
            20, 50, 60, 80, 120, Color.CYAN,
            30, 50, 60, 80, 120, Color.GREEN);
    this.model1.addMotion("S",
            10, 30, 40, 10, 10, Color.RED,
            25, 15, 20, 10, 20, Color.RED);
    this.model1.addMotion("C",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 10, 20, Color.ORANGE);
    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);
    this.model1.addMotion("S",
            35, 15, 20, 6, 7, Color.RED,
            45, 15, 20, 6, 7, Color.YELLOW);
    this.model1.addMotion("C",
            10, 30, 40, 10, 20, Color.ORANGE,
            15, 30, 40, 0, 0, Color.ORANGE);
  }

  /**
   * Tests to make sure the correct output is generated when you add all of the motions for each
   * shape before adding the next shape.
   */
  @Test
  public void testPrintShapesMotionsTogether() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            10, 30, 40, 40, 60, Color.CYAN,
            15, 45, 60, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            15, 45, 60, 40, 60, Color.CYAN,
            30, 50, 60, 80, 120, Color.GREEN);

    this.model1.addMotion("C",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 10, 20, Color.ORANGE);
    this.model1.addMotion("C",
            10, 30, 40, 10, 20, Color.ORANGE,
            15, 30, 40, 0, 0, Color.ORANGE);
    this.model1.addMotion("C",
            15, 30, 40, 0, 0, Color.ORANGE,
            30, 45, 60, 0, 0, Color.ORANGE);

    this.model1.addMotion("S",
            10, 30, 40, 10, 10, Color.RED,
            25, 15, 20, 10, 20, Color.RED);
    this.model1.addMotion("S",
            25, 15, 20, 10, 20, Color.RED,
            35, 15, 20, 6, 7, Color.RED);
    this.model1.addMotion("S",
            35, 15, 20, 6, 7, Color.RED,
            45, 15, 20, 6, 7, Color.YELLOW);
    assertEquals(
            "shape R rectangle\n" +
                    "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n" +
                    "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t15 45.0 60.0 40.0 60.0 0 255 255\n" +
                    "motion R 15 45.0 60.0 40.0 60.0 0 255 255\t30 50.0 60.0 80.0 120.0 0 255 0\n" +
                    "\n" +
                    "shape C ellipse\n" +
                    "motion C 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 10.0 20.0 255 200 0\n" +
                    "motion C 10 30.0 40.0 10.0 20.0 255 200 0\t15 30.0 40.0 0.0 0.0 255 200 0\n" +
                    "motion C 15 30.0 40.0 0.0 0.0 255 200 0\t30 45.0 60.0 0.0 0.0 255 200 0\n" +
                    "\n" +
                    "shape S rectangle\n" +
                    "motion S 10 30.0 40.0 10.0 10.0 255 0 0\t25 15.0 20.0 10.0 20.0 255 0 0\n" +
                    "motion S 25 15.0 20.0 10.0 20.0 255 0 0\t35 15.0 20.0 6.0 7.0 255 0 0\n" +
                    "motion S 35 15.0 20.0 6.0 7.0 255 0 0\t45 15.0 20.0 6.0 7.0 255 255 0",
            this.model1.printAnimations());
  }

  // Tests for IModelImpl#addMotion(String, int, double, double, double, double, Color,
  // int, double, double, double, double, Color)

  /**
   * Tests that attempting to add a motion with a {@code null} id throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNullID() {
    this.model1.addMotion(null, 1, 1, 1, 1, 1, Color.PINK,
            10, 10, 10, 10, 10, Color.PINK);
  }

  /**
   * Tests that attempting to add a motion with a {@code null} starting color throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionSColor() {
    this.model1.addMotion("ID", 1, 1, 1, 1, 1, null,
            10, 10, 10, 10, 10, Color.PINK);
  }

  /**
   * Tests that attempting to add a motion with a {@code null} ending color throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionEColor() {
    this.model1.addMotion("ID", 1, 1, 1, 1, 1, Color.PINK,
            10, 10, 10, 10, 10, null);
  }

  /**
   * Tests that attempting to add a motion with a {@code null} id and {@code null} starting and
   * ending colors throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionAll() {
    this.model1.addMotion(null, 1, 1, 1, 1, 1, null,
            10, 10, 10, 10, 10, null);
  }

  /**
   * Tests that attempting to add a motion with a negative start tick throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNegSTick() {
    this.model1.addMotion("ID", -1, 1, 1, 1, 1, Color.GREEN,
            10, 10, 10, 10, 10, Color.GREEN);
  }

  /**
   * Tests that attempting to add a motion with a negative start width throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNegSWidth() {
    this.model1.addMotion("ID", 1, 1, 1, -1, 1, Color.GREEN,
            10, 10, 10, 10, 10, Color.GREEN);
  }

  /**
   * Tests that attempting to add a motion with a negative start height throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNegSHeight() {
    this.model1.addMotion("ID", 1, 1, 1, 1, -1, Color.GREEN,
            10, 10, 10, 10, 10, Color.GREEN);
  }

  /**
   * Tests that attempting to add a motion with a negative end tick throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNegETick() {
    this.model1.addMotion("ID", 1, 1, 1, 1, 1, Color.GREEN,
            -10, 10, 10, 10, 10, Color.GREEN);
  }

  /**
   * Tests that attempting to add a motion with a negative end width throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNegEWidth() {
    this.model1.addMotion("ID", 1, 1, 1, 1, 1, Color.GREEN,
            10, 10, 10, -10, 10, Color.GREEN);
  }

  /**
   * Tests that attempting to add a motion with a negative end height throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNegEHeight() {
    this.model1.addMotion("ID", 1, 1, 1, 1, 1, Color.GREEN,
            10, 10, 10, 10, -10, Color.GREEN);
  }

  /**
   * Tests that attempting to add a motion with a negative start tick, width, and height, and a
   * negative end tick, width, and height throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNegAll() {
    this.model1.addMotion("ID", -1, 1, 1, -1, -1, Color.GREEN,
            -10, 10, 10, -10, -10, Color.GREEN);
  }

  /**
   * Tests that attempting to add a motion with a endTick that is less than the startTick throws an
   * error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionEndLessStart() {
    this.model1.addMotion("ID", 20, 1, 1, 1, 1, Color.GREEN,
            10, 10, 10, 10, 10, Color.GREEN);
  }

  /**
   * Tests that attempting to add a motion with a endTick that is equal to the startTick throws an
   * error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionEndEqualStart() {
    this.model1.addMotion("ID", 20, 1, 1, 1, 1, Color.GREEN,
            20, 10, 10, 10, 10, Color.GREEN);
  }

  /**
   * Tests that attempting to add a motion to a shape for a model without any IShapes in it throws
   * an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionEmpty() {
    this.model1.addMotion("ID", 1, 0, 0, 20, 20, Color.ORANGE,
            10, 10, 10, 20, 20, Color.BLACK);
  }

  /**
   * Tests that attempting to add a motion to a shape for a model with a non-existent id throws an
   * error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvID() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.addMotion("ID", 1, 0, 0, 20, 20, Color.ORANGE,
            10, 10, 10, 20, 20, Color.BLACK);
  }

  /**
   * Tests that attempting to add a motion to a shape for a model with a lowercase version of an id
   * (R) throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionLowerIDR() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.addMotion("r", 1, 0, 0, 20, 20, Color.ORANGE,
            10, 10, 10, 20, 20, Color.BLACK);
  }

  /**
   * Tests that attempting to add a motion to a shape for a model with a lowercase version of an id
   * (C) throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionLowerIDC() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.addMotion("c", 1, 0, 0, 20, 20, Color.ORANGE,
            10, 10, 10, 20, 20, Color.BLACK);
  }

  /**
   * Tests that attempting to add a motion to a shape for a model with a lowercase version of an id
   * (S) throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionLowerIDS() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.addMotion("s", 1, 0, 0, 20, 20, Color.ORANGE,
            10, 10, 10, 20, 20, Color.BLACK);
  }

  /**
   * Tests that attempting to add a motion to a shape for a model with a uppercase version of an id
   * (r) throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionUpperIDR() {
    this.model1.addShape("r", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("c", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("s", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.addMotion("R", 1, 0, 0, 20, 20, Color.ORANGE,
            10, 10, 10, 20, 20, Color.BLACK);
  }

  /**
   * Tests that attempting to add a motion to a shape for a model with a uppercase version of an id
   * (c) throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionUpperIDC() {
    this.model1.addShape("r", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("c", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("s", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.addMotion("C", 1, 0, 0, 20, 20, Color.ORANGE,
            10, 10, 10, 20, 20, Color.BLACK);
  }

  /**
   * Tests that attempting to add a motion to a shape for a model with a uppercase version of an id
   * (s) throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionUpperIDS() {
    this.model1.addShape("r", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("c", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("s", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.addMotion("S", 1, 0, 0, 20, 20, Color.ORANGE,
            10, 10, 10, 20, 20, Color.BLACK);
  }

  /**
   * Tests that attempting to add a motion that overlaps with the beginning of the first motion of
   * the first shape in the model throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionOverlapFirst() {

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addMotion("R", 10, 10, 20, 30, 40, Color.CYAN,
            20, 10, 40, 30, 40, Color.CYAN);
    this.model1.addMotion("R", 20, 10, 40, 30, 40, Color.CYAN,
            35, 10, 40, 60, 120, Color.CYAN);
    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            45, 20, 40, 60, 120, Color.DARK_GRAY);

    // This call should throw an error
    this.model1.addMotion("R", 0, 10, 20, 30, 40, Color.CYAN,
            15, 10, 40, 30, 40, Color.CYAN);
  }

  /**
   * Tests that attempting to add a motion that overlaps with the beginning of the second motion of
   * the first shape in the model throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionOverlapSecondS() {

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addMotion("R", 10, 10, 20, 30, 40, Color.CYAN,
            20, 10, 40, 30, 40, Color.CYAN);
    this.model1.addMotion("R", 20, 10, 40, 30, 40, Color.CYAN,
            35, 10, 40, 60, 120, Color.CYAN);
    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            45, 20, 40, 60, 120, Color.DARK_GRAY);

    // This call should throw an error
    this.model1.addMotion("R", 20, 10, 20, 30, 40, Color.CYAN,
            27, 10, 40, 30, 40, Color.CYAN);
  }

  /**
   * Tests that attempting to add a motion that overlaps with the ending of the second motion of the
   * first shape in the model throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionOverlapSecondE() {

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addMotion("R", 10, 10, 20, 30, 40, Color.CYAN,
            20, 10, 40, 30, 40, Color.CYAN);
    this.model1.addMotion("R", 20, 10, 40, 30, 40, Color.CYAN,
            35, 10, 40, 60, 120, Color.CYAN);
    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            45, 20, 40, 60, 120, Color.DARK_GRAY);

    // This call should throw an error
    this.model1.addMotion("R", 27, 10, 20, 30, 40, Color.CYAN,
            38, 10, 40, 30, 40, Color.CYAN);
  }

  /**
   * Tests that attempting to add a motion that overlaps with the beginning of the third motion of
   * the first shape in the model throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionOverlapThirdS() {

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addMotion("R", 10, 10, 20, 30, 40, Color.CYAN,
            20, 10, 40, 30, 40, Color.CYAN);
    this.model1.addMotion("R", 20, 10, 40, 30, 40, Color.CYAN,
            35, 10, 40, 60, 120, Color.CYAN);
    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            45, 20, 40, 60, 120, Color.DARK_GRAY);

    // This call should throw an error
    this.model1.addMotion("R", 38, 10, 20, 30, 40, Color.CYAN,
            41, 10, 40, 30, 40, Color.CYAN);
  }

  /**
   * Tests that attempting to add a motion that overlaps with the ending of the third motion of the
   * first shape in the model throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionOverlapThirdE() {

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addMotion("R", 10, 10, 20, 30, 40, Color.CYAN,
            20, 10, 40, 30, 40, Color.CYAN);
    this.model1.addMotion("R", 20, 10, 40, 30, 40, Color.CYAN,
            35, 10, 40, 60, 120, Color.CYAN);
    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            45, 20, 40, 60, 120, Color.DARK_GRAY);

    // This call should throw an error
    this.model1.addMotion("R", 44, 20, 40, 60, 120, Color.DARK_GRAY,
            50, 10, 40, 30, 40, Color.CYAN);
  }

  /**
   * Tests that attempting to add a motion that overlaps with the all of the motions of the first
   * shape in the model throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionOverlapAll() {

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addMotion("R", 10, 10, 20, 30, 40, Color.CYAN,
            20, 10, 40, 30, 40, Color.CYAN);
    this.model1.addMotion("R", 20, 10, 40, 30, 40, Color.CYAN,
            35, 10, 40, 60, 120, Color.CYAN);
    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            45, 20, 40, 60, 120, Color.DARK_GRAY);

    // This call should throw an error
    this.model1.addMotion("R", 0, 10, 20, 30, 40, Color.CYAN,
            100, 10, 40, 30, 40, Color.CYAN);
  }

  /**
   * Tests that attempting to add a motion that overlaps with the first two motions of the first
   * shape in the model throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionOverlapFirstTwo() {

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addMotion("R", 10, 10, 20, 30, 40, Color.CYAN,
            20, 10, 40, 30, 40, Color.CYAN);
    this.model1.addMotion("R", 20, 10, 40, 30, 40, Color.CYAN,
            35, 10, 40, 60, 120, Color.CYAN);
    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            45, 20, 40, 60, 120, Color.DARK_GRAY);

    // This call should throw an error
    this.model1.addMotion("R", 0, 10, 20, 30, 40, Color.CYAN,
            37, 10, 40, 30, 40, Color.CYAN);
  }

  /**
   * Tests that attempting to add a motion that overlaps with the last motion of the first shape in
   * the model throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionOverlapLast() {

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addMotion("R", 10, 10, 20, 30, 40, Color.CYAN,
            20, 10, 40, 30, 40, Color.CYAN);
    this.model1.addMotion("R", 20, 10, 40, 30, 40, Color.CYAN,
            35, 10, 40, 60, 120, Color.CYAN);
    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            45, 20, 40, 60, 120, Color.DARK_GRAY);

    // This call should throw an error
    this.model1.addMotion("R", 37, 10, 40, 60, 120, Color.CYAN,
            50, 10, 40, 30, 40, Color.CYAN);
  }

  /**
   * Tests that attempting to add a motion whose start state doesn't match up with the previous
   * motion's end state (in a given shape's list of motions) will throw an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionBadStart() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addMotion("R", 10, 10, 20, 30, 40, Color.CYAN,
            20, 10, 40, 30, 40, Color.CYAN);
    this.model1.addMotion("R", 20, 10, 40, 30, 40, Color.CYAN,
            35, 10, 40, 60, 120, Color.CYAN);
    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            45, 20, 40, 60, 120, Color.DARK_GRAY);

    this.model1.addMotion("R", 35, 5, 10, 15, 20, Color.GREEN,
            40, 20, 40, 60, 120, Color.CYAN);
  }

  /**
   * Tests that attempting to add a motion whose end state doesn't match up with the next motion's
   * start state (in a given shape's list of motions) will throw an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionBadEnd() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addMotion("R", 10, 10, 20, 30, 40, Color.CYAN,
            20, 10, 40, 30, 40, Color.CYAN);
    this.model1.addMotion("R", 20, 10, 40, 30, 40, Color.CYAN,
            35, 10, 40, 60, 120, Color.CYAN);
    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            45, 20, 40, 60, 120, Color.DARK_GRAY);

    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            40, 10, 15, 19, 25, Color.RED);
  }

  /**
   * Tests that attempting to add a motion whose end state doesn't match up with the next motion's
   * start state, (in a given shape's list of motions), and vice versa, will throw an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionBadBoth() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addMotion("R", 10, 10, 20, 30, 40, Color.CYAN,
            20, 10, 40, 30, 40, Color.CYAN);
    this.model1.addMotion("R", 20, 10, 40, 30, 40, Color.CYAN,
            35, 10, 40, 60, 120, Color.CYAN);
    this.model1.addMotion("R", 40, 10, 40, 60, 120, Color.CYAN,
            45, 20, 40, 60, 120, Color.DARK_GRAY);

    this.model1.addMotion("R", 35, 5, 10, 15, 20, Color.GREEN,
            40, 10, 15, 19, 25, Color.RED);
  }

  /**
   * Tests for adding a motion into a model with one shape in it that has no motions.
   */
  @Test
  public void testAddMotion1ShapeEmpty() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    assertEquals("shape R rectangle", this.model1.printAnimations());

    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);

    assertEquals("shape R rectangle\n"
                    + "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255",
            this.model1.printAnimations());
  }

  /**
   * Tests for adding a motion into a model with one shape in it that has 1 motion.
   */
  @Test
  public void testAddMotion1Shape1() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    assertEquals("shape R rectangle", this.model1.printAnimations());

    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);

    assertEquals("shape R rectangle\n"
                    + "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255",
            this.model1.printAnimations());

    this.model1.addMotion("R",
            10, 30, 40, 40, 60, Color.CYAN,
            15, 45, 60, 40, 60, Color.CYAN);

    assertEquals("shape R rectangle\n"
                    + "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n"
                    + "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t15 45.0 60.0 40.0 60.0 0 255 255",
            this.model1.printAnimations());
  }

  /**
   * Tests for adding a motion into a model with one shape in it that has three motions. The motion
   * should be added to the beginning of the shape's motion list.
   */
  @Test
  public void testAddMotion1Shape3MotBeg() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    assertEquals("shape R rectangle", this.model1.printAnimations());

    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            10, 30, 40, 40, 60, Color.CYAN,
            15, 45, 60, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            15, 45, 60, 40, 60, Color.CYAN,
            30, 50, 60, 80, 120, Color.GREEN);

    assertEquals("shape R rectangle\n" +
                    "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n" +
                    "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t15 45.0 60.0 40.0 60.0 0 255 255\n" +
                    "motion R 15 45.0 60.0 40.0 60.0 0 255 255\t30 50.0 60.0 80.0 120.0 0 255 0",
            this.model1.printAnimations());
  }


  /**
   * Tests for adding in a motion to a model with one shape, where the motion will have all fields
   * of 0, except for the end tick.
   */
  @Test
  public void testAddMotionZero() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);

    assertEquals("shape R rectangle", this.model1.printAnimations());

    this.model1.addMotion("R", 0, 0, 0, 0, 0, Color.BLACK,
            1, 0, 0, 0, 0, Color.BLACK);

    assertEquals("shape R rectangle\n"
                    + "motion R 0 0.0 0.0 0.0 0.0 0 0 0\t1 0.0 0.0 0.0 0.0 0 0 0",
            this.model1.printAnimations());
  }

  // Tests for IModelImpl#addShape(String, ShapeType, double, double, double, double, Color)

  /**
   * Tests that attempting to add a shape with a {@code null} id throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullID() {
    this.model1.addShape(null, ShapeType.RECTANGLE, 4.5, 6.4, 3.4, 7, Color.WHITE);
  }

  /**
   * Tests that attempting to add a shape with a {@code null} ShapeType throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullType() {
    this.model1.addShape("R", null, 4.5, 6.4, 3.4, 7, Color.WHITE);
  }

  /**
   * Tests that attempting to add a shape with a {@code null} Color throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullColor() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 4.5, 6.4, 3.4, 7, null);
  }

  /**
   * Tests that attempting to add a shape with a {@code null} id, ShapeType, and Color throws an
   * error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullAll() {
    this.model1.addShape(null, null, 4.5, 6.4, 3.4, 7, null);
  }

  /**
   * Tests that attempting to add a shape with a negative width throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNegWidth() {
    this.model1.addShape("R", ShapeType.RECTANGLE, -10, 20, 30, 40, Color.CYAN);
  }

  /**
   * Tests that attempting to add a shape with a negative height throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNegHeight() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, -20, 30, 40, Color.CYAN);
  }

  /**
   * Tests that attempting to add a shape with a negative height throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNegBoth() {
    this.model1.addShape("R", ShapeType.RECTANGLE, -10, -20, 30, 40, Color.CYAN);
  }

  /**
   * Tests that attempting to add a shape with an id that is the same as the first shape in the
   * model throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeSameFirstID() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    // This call should throw an error
    this.model1.addShape("R", ShapeType.ELLIPSE, 1.2, 3.4, 5.6, 7.8, Color.BLACK);
  }

  /**
   * Tests that attempting to add a shape with an id that is the same as the second shape in the
   * model throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeSameSecondID() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    // This call should throw an error
    this.model1.addShape("C", ShapeType.RECTANGLE, 1.2, 3.4, 5.6, 7.8, Color.BLACK);
  }

  /**
   * Tests that attempting to add a shape with an id that is the same as the third shape in the
   * model throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeSameThirdID() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    // This call should throw an error
    this.model1.addShape("S", ShapeType.ELLIPSE, 1.2, 3.4, 5.6, 7.8, Color.BLACK);
  }

  /**
   * Tests for adding a shape to a model without any shapes in it.
   */
  @Test
  public void testAddShapeEmpty() {
    assertEquals("", this.model1.printAnimations());
    this.model1.addShape("R", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);
    assertEquals("shape R rectangle", this.model1.printAnimations());
  }

  /**
   * Tests for adding a shape to a model with 1 shape in it.
   */
  @Test
  public void testAddShapeWith1() {
    assertEquals("", this.model1.printAnimations());

    // Tested in testAddEmpty
    this.model1.addShape("R", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);

    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);

    assertEquals(
            "shape R rectangle\n\n"
                    + "shape C ellipse",
            this.model1.printAnimations());
  }

  /**
   * Tests for adding a shape to a model with 2 shapes in it.
   */
  @Test
  public void testAddShapeWith2() {
    assertEquals("", this.model1.printAnimations());

    // Tested in testAddWith1
    this.model1.addShape("R", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);

    this.model1.addShape("S", ShapeType.RECTANGLE, 4, 5.9, 6.8, 1000, Color.RED);

    assertEquals(
            "shape R rectangle\n\n"
                    + "shape C ellipse\n\n"
                    + "shape S rectangle",
            this.model1.printAnimations());
  }

  /**
   * Tests for adding a shape with the same id as the first shape in the model, but in lowercase.
   */
  @Test
  public void testAddShapeSameIDLower1() {
    assertEquals("", this.model1.printAnimations());

    // Tested in testAddWith2
    this.model1.addShape("R", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 4, 5.9, 6.8, 1000, Color.RED);

    this.model1.addShape("r", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);

    assertEquals(
            "shape R rectangle\n\n"
                    + "shape C ellipse\n\n"
                    + "shape S rectangle\n\n"
                    + "shape r rectangle",
            this.model1.printAnimations());
  }

  /**
   * Tests for adding a shape with the same id as the second shape in the model, but in lowercase.
   */
  @Test
  public void testAddShapeSameIDLower2() {
    assertEquals("", this.model1.printAnimations());

    // Tested in testAddWith2
    this.model1.addShape("R", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 4, 5.9, 6.8, 1000, Color.RED);

    this.model1.addShape("c", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);

    assertEquals(
            "shape R rectangle\n\n"
                    + "shape C ellipse\n\n"
                    + "shape S rectangle\n\n"
                    + "shape c ellipse",
            this.model1.printAnimations());
  }

  /**
   * Tests for adding a shape with the same id as the second shape in the model, but in lowercase.
   */
  @Test
  public void testAddShapeSameIDLower3() {
    assertEquals("", this.model1.printAnimations());

    // Tested in testAddWith2
    this.model1.addShape("R", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 4, 5.9, 6.8, 1000, Color.RED);

    this.model1.addShape("s", ShapeType.RECTANGLE, 4, 5.9, 6.8, 1000, Color.RED);

    assertEquals(
            "shape R rectangle\n\n"
                    + "shape C ellipse\n\n"
                    + "shape S rectangle\n\n"
                    + "shape s rectangle",
            this.model1.printAnimations());
  }

  /**
   * Tests for adding a shape with the same id as the first shape in the model, but in uppercase.
   */
  @Test
  public void testAddShapeSameIDUpper1() {
    assertEquals("", this.model1.printAnimations());

    // Tested in testAddWith2
    this.model1.addShape("r", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);
    this.model1.addShape("c", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("s", ShapeType.RECTANGLE, 4, 5.9, 6.8, 1000, Color.RED);

    this.model1.addShape("R", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);

    assertEquals(
            "shape r rectangle\n\n"
                    + "shape c ellipse\n\n"
                    + "shape s rectangle\n\n"
                    + "shape R rectangle",
            this.model1.printAnimations());
  }

  /**
   * Tests for adding a shape with the same id as the second shape in the model, but in uppercase.
   */
  @Test
  public void testAddShapeSameIDUpper2() {
    assertEquals("", this.model1.printAnimations());

    // Tested in testAddWith2
    this.model1.addShape("r", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);
    this.model1.addShape("c", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("s", ShapeType.RECTANGLE, 4, 5.9, 6.8, 1000, Color.RED);

    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);

    assertEquals(
            "shape r rectangle\n\n"
                    + "shape c ellipse\n\n"
                    + "shape s rectangle\n\n"
                    + "shape C ellipse",
            this.model1.printAnimations());
  }

  /**
   * Tests for adding a shape with the same id as the third shape in the model, but in uppercase.
   */
  @Test
  public void testAddShapeSameIDUpper3() {
    assertEquals("", this.model1.printAnimations());

    // Tested in testAddWith2
    this.model1.addShape("r", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);
    this.model1.addShape("c", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("s", ShapeType.RECTANGLE, 4, 5.9, 6.8, 1000, Color.RED);

    this.model1.addShape("S", ShapeType.RECTANGLE, 4, 5.9, 6.8, 1000, Color.RED);

    assertEquals(
            "shape r rectangle\n\n"
                    + "shape c ellipse\n\n"
                    + "shape s rectangle\n\n"
                    + "shape S rectangle",
            this.model1.printAnimations());
  }

  /**
   * Tests for adding a shape with an id with multiple characters in an model.
   */
  @Test
  public void testAddShapeMultCharIDEmpty() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("hdsafhufhf17483%^$*&ihfh346&*&$./';]",
            ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);

    assertEquals("shape hdsafhufhf17483%^$*&ihfh346&*&$./';] rectangle",
            this.model1.printAnimations());
  }

  /**
   * Tests for adding a shape with an id with multiple characters in an non-empty model.
   */
  @Test
  public void testAddShapeMultCharIDNonEmpty() {
    assertEquals("", this.model1.printAnimations());

    // Tested in testAddWith2
    this.model1.addShape("R", ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 4, 5.9, 6.8, 1000, Color.RED);

    this.model1.addShape("hdsafhufhf17483%^$*&ihfh346&*&$./';]",
            ShapeType.RECTANGLE, 10.3, 20.5, 30.7, 40.9, Color.CYAN);

    assertEquals(
            "shape R rectangle\n\n"
                    + "shape C ellipse\n\n"
                    + "shape S rectangle\n\n"
                    + "shape hdsafhufhf17483%^$*&ihfh346&*&$./';] rectangle",
            this.model1.printAnimations());
  }

  /**
   * Tests that adding a shape with a width and height of 0 adds the shape in normally (with no
   * errors).
   */
  @Test
  public void testAddShapeWidthHeight0() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 0, 0, 30.7, 40.9, Color.CYAN);

    assertEquals("shape R rectangle", this.model1.printAnimations());
  }

  // Tests for IModelImpl#removeShape(String)

  /**
   * Tests that attempting to remove a shape from a model with a {@code null} id throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNull() {
    this.model1.removeShape(null);
  }

  /**
   * Tests that attempting to remove a shape from a model without any IShapes in it throws an
   * error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveEmpty() {
    this.model1.removeShape("ID");
  }

  /**
   * Tests that attempting to remove a shape from a model with a non-existent id throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveInvID() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.removeShape("ID");
  }

  /**
   * Tests that attempting to remove a shape from a model with a lowercase version of an id (R)
   * throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLowerIDR() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.removeShape("r");
  }

  /**
   * Tests that attempting to remove a shape from a model with a lowercase version of an id (C)
   * throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLowerIDC() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.removeShape("c");
  }

  /**
   * Tests that attempting to remove a shape from a model with a lowercase version of an id (S)
   * throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLowerIDS() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.removeShape("s");
  }

  /**
   * Tests that attempting to remove a shape from a model with a uppercase version of an id (r)
   * throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveUpperIDR() {
    this.model1.addShape("r", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("c", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("s", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.removeShape("R");
  }

  /**
   * Tests that attempting to remove a shape from a model with a uppercase version of an id (c)
   * throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveUpperIDC() {
    this.model1.addShape("r", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("c", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("s", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.removeShape("C");
  }

  /**
   * Tests that attempting to remove a shape from a model with a uppercase version of an id (s)
   * throws an error.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveUpperIDS() {
    this.model1.addShape("r", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("c", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("s", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);
    this.model1.removeShape("S");
  }

  /**
   * Tests for removing the first shape in a model's list of shapes (without any motions added).
   */
  @Test
  public void testRemoveFirstNoMot() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    assertEquals("shape R rectangle\n\n"
            + "shape C ellipse\n\n"
            + "shape S rectangle", this.model1.printAnimations());
    this.model1.removeShape("R");
    assertEquals("shape C ellipse\n\n"
            + "shape S rectangle", this.model1.printAnimations());
  }

  /**
   * Tests for removing the second shape in a model's list of shapes (without any motions added).
   */
  @Test
  public void testRemoveSecondNoMot() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    assertEquals("shape R rectangle\n\n"
            + "shape C ellipse\n\n"
            + "shape S rectangle", this.model1.printAnimations());
    this.model1.removeShape("C");
    assertEquals("shape R rectangle\n\n"
            + "shape S rectangle", this.model1.printAnimations());
  }

  /**
   * Tests for removing the third shape in a model's list of shapes (without any motions added).
   */
  @Test
  public void testRemoveThirdNoMot() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    assertEquals("shape R rectangle\n\n"
            + "shape C ellipse\n\n"
            + "shape S rectangle", this.model1.printAnimations());
    this.model1.removeShape("S");
    assertEquals("shape R rectangle\n\n"
            + "shape C ellipse", this.model1.printAnimations());
  }

  /**
   * Tests for removing the first shape in a model's list of shapes (with motions added).
   */
  @Test
  public void testRemoveFirstWithMot() {
    assertEquals("", this.model1.printAnimations());

    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            10, 30, 40, 40, 60, Color.CYAN,
            15, 45, 60, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            15, 45, 60, 40, 60, Color.CYAN,
            30, 45, 60, 40, 60, Color.GREEN);

    this.model1.addMotion("C",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 10, 20, Color.ORANGE);
    this.model1.addMotion("C",
            10, 30, 40, 10, 20, Color.ORANGE,
            15, 30, 40, 0, 0, Color.ORANGE);
    this.model1.addMotion("C",
            15, 30, 40, 0, 0, Color.ORANGE,
            30, 45, 60, 0, 0, Color.ORANGE);

    this.model1.addMotion("S",
            10, 30, 40, 10, 10, Color.RED,
            25, 15, 20, 10, 20, Color.RED);
    this.model1.addMotion("S",
            25, 15, 20, 10, 20, Color.RED,
            35, 15, 20, 6, 7, Color.RED);
    this.model1.addMotion("S",
            35, 15, 20, 6, 7, Color.RED,
            45, 15, 20, 6, 7, Color.YELLOW);

    assertEquals(
            "shape R rectangle\n" +
                    "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n" +
                    "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t15 45.0 60.0 40.0 60.0 0 255 255\n" +
                    "motion R 15 45.0 60.0 40.0 60.0 0 255 255\t30 45.0 60.0 40.0 60.0 0 255 0\n" +
                    "\n" +
                    "shape C ellipse\n" +
                    "motion C 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 10.0 20.0 255 200 0\n" +
                    "motion C 10 30.0 40.0 10.0 20.0 255 200 0\t15 30.0 40.0 0.0 0.0 255 200 0\n" +
                    "motion C 15 30.0 40.0 0.0 0.0 255 200 0\t30 45.0 60.0 0.0 0.0 255 200 0\n" +
                    "\n" +
                    "shape S rectangle\n" +
                    "motion S 10 30.0 40.0 10.0 10.0 255 0 0\t25 15.0 20.0 10.0 20.0 255 0 0\n" +
                    "motion S 25 15.0 20.0 10.0 20.0 255 0 0\t35 15.0 20.0 6.0 7.0 255 0 0\n" +
                    "motion S 35 15.0 20.0 6.0 7.0 255 0 0\t45 15.0 20.0 6.0 7.0 255 255 0",
            this.model1.printAnimations());
    this.model1.removeShape("R");
    assertEquals(
            "shape C ellipse\n" +
                    "motion C 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 10.0 20.0 255 200 0\n" +
                    "motion C 10 30.0 40.0 10.0 20.0 255 200 0\t15 30.0 40.0 0.0 0.0 255 200 0\n" +
                    "motion C 15 30.0 40.0 0.0 0.0 255 200 0\t30 45.0 60.0 0.0 0.0 255 200 0\n" +
                    "\n" +
                    "shape S rectangle\n" +
                    "motion S 10 30.0 40.0 10.0 10.0 255 0 0\t25 15.0 20.0 10.0 20.0 255 0 0\n" +
                    "motion S 25 15.0 20.0 10.0 20.0 255 0 0\t35 15.0 20.0 6.0 7.0 255 0 0\n" +
                    "motion S 35 15.0 20.0 6.0 7.0 255 0 0\t45 15.0 20.0 6.0 7.0 255 255 0",
            this.model1.printAnimations());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionNullID() {
    this.model1.removeMotionAtStartTick(null, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionNegTick() {
    this.model1.removeMotionAtStartTick("R", -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetMotionsNegTick() {
    this.model1.getMotionsAtTick(-2);
  }

  /**
   * Tests that a motion can be removed from the animation.
   */
  @Test
  public void testRemoveMotion() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            10, 30, 40, 40, 60, Color.CYAN,
            15, 45, 60, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            15, 45, 60, 40, 60, Color.CYAN,
            30, 45, 60, 40, 60, Color.GREEN);

    assertEquals("shape R rectangle\n" +
                    "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n" +
                    "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t15 45.0 60.0 40.0 60.0 0 255 255\n" +
                    "motion R 15 45.0 60.0 40.0 60.0 0 255 255\t30 45.0 60.0 40.0 60.0 0 255 0",
            model1.printAnimations());

    this.model1.removeMotionAtStartTick("R", 15);

    assertEquals("shape R rectangle\n" +
                    "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n" +
                    "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t15 45.0 60.0 40.0 60.0 0 255 255",
            model1.printAnimations());
  }

  /**
   * Tests that nothing happens if the ID cannot be found
   */
  @Test
  public void testRemoveMotion2() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            10, 30, 40, 40, 60, Color.CYAN,
            15, 45, 60, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            15, 45, 60, 40, 60, Color.CYAN,
            30, 45, 60, 40, 60, Color.GREEN);

    assertEquals("shape R rectangle\n" +
                    "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n" +
                    "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t15 45.0 60.0 40.0 60.0 0 255 255\n" +
                    "motion R 15 45.0 60.0 40.0 60.0 0 255 255\t30 45.0 60.0 40.0 60.0 0 255 0",
            model1.printAnimations());

    this.model1.removeMotionAtStartTick("E", 15);

    assertEquals("shape R rectangle\n" +
                    "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n" +
                    "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t15 45.0 60.0 40.0 60.0 0 255 255\n" +
                    "motion R 15 45.0 60.0 40.0 60.0 0 255 255\t30 45.0 60.0 40.0 60.0 0 255 0",
            model1.printAnimations());
  }

  /**
   * Tests that nothing happens if the tick cannot be found
   */
  @Test
  public void testRemoveMotion3() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            10, 30, 40, 40, 60, Color.CYAN,
            15, 45, 60, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            15, 45, 60, 40, 60, Color.CYAN,
            30, 45, 60, 40, 60, Color.GREEN);

    assertEquals("shape R rectangle\n" +
                    "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n" +
                    "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t15 45.0 60.0 40.0 60.0 0 255 255\n" +
                    "motion R 15 45.0 60.0 40.0 60.0 0 255 255\t30 45.0 60.0 40.0 60.0 0 255 0",
            model1.printAnimations());

    this.model1.removeMotionAtStartTick("R", 2);

    assertEquals("shape R rectangle\n" +
                    "motion R 1 30.0 40.0 10.0 20.0 0 255 255\t10 30.0 40.0 40.0 60.0 0 255 255\n" +
                    "motion R 10 30.0 40.0 40.0 60.0 0 255 255\t15 45.0 60.0 40.0 60.0 0 255 255\n" +
                    "motion R 15 45.0 60.0 40.0 60.0 0 255 255\t30 45.0 60.0 40.0 60.0 0 255 0",
            model1.printAnimations());
  }

  /**
   * Tests that removing from an empty animation does nothing
   */
  @Test
  public void testRemoveMotion4() {
    assertEquals("",
            model1.printAnimations());

    this.model1.removeMotionAtStartTick("E", 2);

    assertEquals("",
            model1.printAnimations());
  }

  @Test
  public void testGetMotionsAtTickEmpty() {
    assertEquals(new ArrayList<IMotion>(), model1.getMotionsAtTick(0));
  }

  /**
   * Tests that getting motions at a given tick across shapes works.
   */
  @Test
  public void testGetMotionsAtTick() {
    this.model1.addShape("R", ShapeType.RECTANGLE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("C", ShapeType.ELLIPSE, 10, 20, 30, 40, Color.CYAN);
    this.model1.addShape("S", ShapeType.RECTANGLE, 10, 10, 30, 40, Color.RED);

    this.model1.addMotion("R",
            1, 30, 40, 10, 20, Color.CYAN,
            10, 30, 40, 40, 60, Color.CYAN);
    this.model1.addMotion("R",
            10, 30, 40, 40, 60, Color.CYAN,
            15, 45, 60, 40, 60, Color.CYAN);

    this.model1.addMotion("S",
            10, 30, 40, 10, 10, Color.RED,
            25, 15, 20, 10, 20, Color.RED);

    ArrayList<IMotion> res = new ArrayList<>();
    res.add(model1.getShapes().get(0).getMotions().get(1));
    res.add(model1.getShapes().get(2).getMotions().get(0));

    assertEquals(res, model1.getMotionsAtTick(12));
  }

  /**
   * Tests a builder constructs a model with default values when being called with build.
   */
  @Test
  public void testBuilder() {
    IModel model = this.builder.build();
    assertEquals(0, model.getX());
    assertEquals(0, model.getY());
    assertEquals(200, model.getWidth());
    assertEquals(200, model.getHeight());
    assertEquals(0, model.getMaxX());
    assertEquals(0, model.getMaxY());
    assertEquals(new ArrayList<IModelShape>(), model.getShapes());
  }

  /**
   * Tests that setBounds updates the builders fields
   */
  /**
   * Tests a builder constructs a model with default values when being called with build.
   */
  @Test
  public void testSetBounds() {
    IModel model = this.builder.build();
    assertEquals(0, model.getX());
    assertEquals(0, model.getY());
    assertEquals(200, model.getWidth());
    assertEquals(200, model.getHeight());
    assertEquals(0, model.getMaxX());
    assertEquals(0, model.getMaxY());
    assertEquals(new ArrayList<IModelShape>(), model.getShapes());

    this.builder.setBounds(20, 20, 300, 300);
    model = this.builder.build();
    assertEquals(20, model.getX());
    assertEquals(20, model.getY());
    assertEquals(300, model.getWidth());
    assertEquals(300, model.getHeight());
    assertEquals(0, model.getMaxX());
    assertEquals(0, model.getMaxY());
    assertEquals(new ArrayList<IModelShape>(), model.getShapes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void declareShapeNullID() {
    this.builder.declareShape(null, "rectangle");
  }
}
