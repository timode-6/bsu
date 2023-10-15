<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default5.aspx.cs" Inherits="Default5" %>

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
            <asp:Label ID="Label1" runat="server" Text="Use comma as separator to real numbers!"
                Font-Size="15pt" ForeColor="Red"/>
            <br/>
            <asp:TextBox ID="Number1TB" runat="server"/>
            <asp:TextBox ID="Number2TB" runat="server"/>
            <asp:Button ID="AddButton" runat="server" Text="Add" BackColor="Yellow" 
                OnClick="AddButton_Click" /> &nbsp;
            <asp:Button ID="SubButton" runat="server" Text="Subtract" BackColor="#3366ff" 
                OnClick="SubButton_Click" /> &nbsp;
            <asp:Button ID="MultiplyButton" runat="server" Text="Multiply" BackColor="#ff0066" 
                OnClick="MultiplyButton_Click" />
            &nbsp;
            <asp:Button ID="DivideButton" runat="server" Text="Divide" BackColor="#66ff33" 
                OnClick="DivideButton_Click" />
            &nbsp;
            <br/>
            <asp:Label ID="Result" runat="server" Text="" Visible="false"/>
        </div>
    </form>
</body>
</html>
