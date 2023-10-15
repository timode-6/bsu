<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default3.aspx.cs" Inherits="Default3" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <font size="2", color="blue" face="Arial">
                <asp:Label ID="Label1" runat="server" Text="Пожалуйста, введите ваше имя:"/>
                <br/>
                <asp:TextBox ID="UserNameTextBox" runat="server" Width="100pt"
                    Text="" OnTextChanged="UserNameTextBox_TextChanged"></asp:TextBox>
                <asp:Button ID="Button1" runat="server" Text="Click me" 
                    OnClick="Button1_Click"></asp:Button>
                <br/><br/>
                <asp:Label ID="Label2" runat="server" Text="" Visible="false"/>
            </font>
        </div>
    </form>
</body>
</html>
