import java.awt.Color;
/**
 * concrete implementation of ColorSchemeStrategy
 * @author Nick
 *
 */
public class ColorSchemeLightTheme implements ColorSchemeStrategy {

	private Color colorOne;
	private Color colorTwo;
	private Color colorThree;
	private Color colorFour;
	private Color colorFive;
	private Color fontColor;
	public ColorSchemeLightTheme()
	{
		//Where the concrete color scheme is defined
		this.colorOne = convertFXColorToSwingColor(javafx.scene.paint.Color.GHOSTWHITE); //Dark
		this.colorTwo = convertFXColorToSwingColor(javafx.scene.paint.Color.WHITE); //Light
		this.colorThree = convertFXColorToSwingColor(javafx.scene.paint.Color.LIGHTGRAY);
		this.colorFour = convertFXColorToSwingColor(javafx.scene.paint.Color.NAVY);
		this.colorFive = convertFXColorToSwingColor(javafx.scene.paint.Color.ALICEBLUE);
		this.fontColor = Color.BLACK;

	}

	/**
	 * converts java.fx colors to color object
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
