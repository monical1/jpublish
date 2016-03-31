# Utilities #
There are several utilities which are provided in the context for each request. These utilities are primarily for making certain aspects of view development easier. The following utilities are included:

| **Name** | **Class** | **Description** | **Example** |
|:---------|:----------|:----------------|:------------|
|urlUtilities 	|org.jpublish.util.URLUtilities |	Provides methods for creating plain and secure URLs for links.| 	urlUtilities.buildStandard(path) urlUtilities.buildSecure(path)|
|numberUtilities |	org.jpublish.util.NumberUtilities |	Number formatting. The format method takes a double as the first argument which is the number to be formatted and an integer representing the number of decimal places which should be displayed. |	numberUtilities.format(34.304032, 2)|
|dateUtilities |	org.jpublish.util.DateUtilities |	Date formatting. |	dateUtilities.format(date, "MM/dd/yyyy HH/ss")|