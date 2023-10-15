<%@ Page Language="VB" AutoEventWireup="false" CodeFile="Default4.aspx.vb" Inherits="Default4" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <br/>
            <!--TextBox elements to enter numbers to be muliplied.-->
            <asp:TextBox ID="Number1TB" runat="server" Text=""/>
            <asp:TextBox ID="Number2TB" runat="server" Text=""/>
            <!--On click performs multiplication of numbers. The result is written to 
                the Result Label-->
            <asp:Button ID="Button1" runat="server" Text="Multiply" />
            <br/>
            <!--The result of multiplication is written to this Label.-->
            <asp:Label ID="Result" runat="server" Text="" Visible="false"/>
            <br/>
        </div>
    </form>
</body>
</html>
