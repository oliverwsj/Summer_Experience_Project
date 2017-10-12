import java.awt.Color;
/**
 * concrete implementation of ColorSchemeStrategy
 * @author Nick
 *
 */
public class ColorSchemeColoradoCollege implements ColorSchemeStrategy
{

	private Color colorOne;
	private Color colorTwo;
	private Color colorThree;
	private Color colorFour;
	private Color colorFive;
	private Color fontColor;
	public ColorSchemeColoradoCollege()
	{
		//Where the concrete color scheme is defined
		colorOne = convertFXColorToSwingColor(javafx.scene.paint.Color.GHOSTWHITE); //Dark
		colorTwo = convertFXColorToSwingColor(javafx.scene.paint.Color.GOLDENROD); //Light
		colorThree = convertFXColorToSwingColor(javafx.scene.paint.Color.DARKGOLDENROD);
		colorFour = convertFXColorToSwingColor(javafx.scene.paint.Color.GOLD);
		colorFive = convertFXColorToSwingColor(javafx.scene.paint.Color.ALICEBLUE);
		fontColor = Color.BLACK;

	}

	/**
	 * converts javafx colors to color object
	 * @param fxColor
	 * @return
	 */
	public Color convertFXColorToSwingColor(javafx.scene.paint.Color fxColor)
	{
		java.awt.Color awtColor = new java.awt.Color((float) fxColor.getRed(),
				(float) fxColor.getGreen(),
				(float) fxColor.getBlue(),
				(float) fxColor.getOpacity());
		return awtColor;
	}

	@Override
	public Color getColorOne() 
	{
		return colorOne;
	}
	@Override
	public Color getColorTwo() 
	{
		return colorTwo;
	}
	@Override
	public Color getColorThree()
	{
		return colorThree;
	}
	@Override
	public Color getColorFour() 
	{
		// TODO Auto-generated method stub
		return colorFour;
	}
	@Override
	public Color getColorFive() 
	{
		// TODO Auto-generated method stub
		return colorFive;
	}
	public Color getFontColor() 
	{

		return fontColor;
	}
}
